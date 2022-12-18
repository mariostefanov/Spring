package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportApartmentDTO;
import softuni.exam.models.dto.ImportApartmentsDTO;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final Path apartmentPath;
    private Unmarshaller unmarshaller;
    private final Validator validator;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, Validator validator, TownRepository townRepository) throws JAXBException {
        this.apartmentRepository = apartmentRepository;
        this.validator = validator;
        this.townRepository = townRepository;
        this.modelMapper = new ModelMapper();
        this.apartmentPath = Path.of("src/main/resources/files/xml/apartments.xml");
        JAXBContext context = JAXBContext.newInstance(ImportApartmentsDTO.class);
        unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count()>0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return String.join("\n", Files.readAllLines(apartmentPath));
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        ImportApartmentsDTO apartments = (ImportApartmentsDTO) unmarshaller
                .unmarshal(new FileReader(apartmentPath.toAbsolutePath().toString()));

        return apartments
                .getApartments()
                .stream()
                .map(this::importApartment)
                .collect(Collectors.joining("\n"));
    }

    private String importApartment(ImportApartmentDTO importApartmentDTO) {
        Set<ConstraintViolation<ImportApartmentDTO>> violations =
                this.validator.validate(importApartmentDTO);

        if (!violations.isEmpty()){
            return "Invalid apartment";
        }

        double area = importApartmentDTO.getArea();
        Town town = this.townRepository.findByTownName(importApartmentDTO.getTown()).get();
        Optional<Apartment> apartmentOpt = this.apartmentRepository
                .findApartmentByAreaAndTown(area, town);

        if (apartmentOpt.isPresent()){
            return "Invalid apartment";
        }

        Apartment apartment = this.modelMapper.map(importApartmentDTO, Apartment.class);
        apartment.setTown(this.townRepository.findByTownName(importApartmentDTO.getTown()).get());
        this.apartmentRepository.save(apartment);
        return "Successfully imported apartment " + apartment.getApartmentType() + " - " + apartment.getArea();

    }
}
