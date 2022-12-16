package exam.service.impl;

import exam.model.Town;
import exam.model.dto.ImportTownDTO;
import exam.model.dto.ImportTownsDTO;
import exam.repository.TownRepository;
import exam.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {
    private final Path xmlPath = Path.of("src/main/resources/files/xml/towns.xml");
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;

    public TownServiceImpl(TownRepository townRepository) throws JAXBException {
        this.townRepository = townRepository;
        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        JAXBContext context = JAXBContext.newInstance(ImportTownsDTO.class);
        unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count()>0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return String.join("/n", Files.readAllLines(xmlPath));
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {

        ImportTownsDTO towns = (ImportTownsDTO) unmarshaller
                .unmarshal(new FileReader(xmlPath.toAbsolutePath().toString()));

        return towns
                .getTowns()
                .stream()
                .map(this::importTown)
                .collect(Collectors.joining("\n"));
    }

    private String importTown(ImportTownDTO importTownDTO) {

        Set<ConstraintViolation<ImportTownDTO>> violations = this.validator.validate(importTownDTO);

        if (!violations.isEmpty()){
            return "Invalid Town";
        }

        Optional<Town> townOpt = this.townRepository.findByNameAndPopulationAndTravelGuide(
                importTownDTO.getName(),
                importTownDTO.getPopulation(),
                importTownDTO.getTravelGuide()
        );

        if (townOpt.isPresent()){
            return "Invalid Town";
        }

        Town town = this.modelMapper.map(importTownDTO, Town.class);

        this.townRepository.save(town);

        return "Successfully imported Town " + town.getName();
    }
}
