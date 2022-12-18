package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportOfferDTO;
import softuni.exam.models.dto.ImportOffersDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.enums.ApartmentType;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final ApartmentType apartmentType = ApartmentType.three_rooms;
    private final Path offerPath;
    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;
    private final AgentRepository agentRepository;
    private final ApartmentRepository apartmentRepository;

    public OfferServiceImpl(Validator validator, ModelMapper modelMapper, OfferRepository offerRepository, AgentRepository agentRepository, ApartmentRepository apartmentRepository) throws JAXBException {
        this.validator = validator;
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
        this.agentRepository = agentRepository;
        this.apartmentRepository = apartmentRepository;
        this.offerPath = Path.of("src/main/resources/files/xml/offers.xml");
        JAXBContext context = JAXBContext.newInstance(ImportOffersDTO.class);
        unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return String.join("\n", Files.readAllLines(offerPath));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        ImportOffersDTO offers = (ImportOffersDTO) unmarshaller
                .unmarshal(new FileReader(offerPath.toAbsolutePath().toString()));

        return offers
                .getOffers()
                .stream()
                .map(this::importOffer)
                .collect(Collectors.joining("\n"));
    }

    private String importOffer(ImportOfferDTO importOfferDTO) {
        Set<ConstraintViolation<ImportOfferDTO>> violations =
                validator.validate(importOfferDTO);

        if (!violations.isEmpty()) {
            return "Invalid offer";
        }

        Optional<Agent> agentOpt =
                this.agentRepository.findByFirstName(importOfferDTO.getAgentName().getName());

        if (agentOpt.isEmpty()) {
            return "Invalid offer";
        }

        Apartment apartment = this.apartmentRepository.findById(importOfferDTO.getApartmentId().getId());

        Offer offer = this.modelMapper.map(importOfferDTO, Offer.class);
        offer.setAgent(agentOpt.get());
        offer.setApartment(apartment);
        this.offerRepository.save(offer);

        return "Successfully imported offer " + offer.getPrice();
    }

    @Override
    public String exportOffers() {
        StringBuilder result = new StringBuilder();
        List<Offer> bestOffers = this.offerRepository.findAllByApartment_ApartmentTypeOrderByApartment_AreaDescPriceAsc(apartmentType);

        for (Offer offer : bestOffers) {
            String offerToString = String.format("Agent %s %s with offerToString №%d:%n" +
                            "   -Apartment area: %.2f%n" +
                            "   --Town: %s%n" +
                            "   ---Price: %.2f$%n"
                    , offer.getAgent().getFirstName()
                    , offer.getAgent().getLastName()
                    , offer.getId()
                    , offer.getApartment().getArea()
                    , offer.getApartment().getTown().getTownName()
                    , offer.getPrice());
            result.append(offerToString);
            result.append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
