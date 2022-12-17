package exam.service.impl;

import exam.model.Shop;
import exam.model.Town;
import exam.model.dto.ImportShopDTO;
import exam.model.dto.ImportShopsDTO;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
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
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;

    private final Path xmlPath= Path.of("src/main/resources/files/xml/shops.xml");

    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;
    private final TownRepository townRepository;

    public ShopServiceImpl(ShopRepository shopRepository, TownRepository townRepository) throws JAXBException {
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;
        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        JAXBContext context = JAXBContext.newInstance(ImportShopsDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count()>0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return String.join("\n", Files.readAllLines(xmlPath));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        ImportShopsDTO shops =
                (ImportShopsDTO) unmarshaller.unmarshal(new FileReader(xmlPath.toAbsolutePath().toString()));

        return shops
                .getShops()
                .stream()
                .map(this::importShop)
                .collect(Collectors.joining("\n"));
    }

    private String importShop(ImportShopDTO importShopDTO) {
        Set<ConstraintViolation<ImportShopDTO>> violations =
                validator.validate(importShopDTO);

        if (!violations.isEmpty()){
            return "Invalid Shop1";
        }

        String townByName = importShopDTO.getTownNameDto().getName();
        Optional<Town> townOpt = this.townRepository.findByName(townByName);

        if (townOpt.isEmpty()) {
            return "Invalid Shop";
        }

        Optional<Shop> shopOpt = this.shopRepository
                .findByAddressAndEmployeeCountAndIncomeAndNameAndShopAreaAndTownName(
                        importShopDTO.getAddress(),
                        importShopDTO.getEmployeeCount(),
                        importShopDTO.getIncome(),
                        importShopDTO.getName(),
                        importShopDTO.getShopAria(),
                        townOpt.get().getName()
                        );

        if (shopOpt.isPresent()){
            return "Invalid Shop2";
        }

        Shop shop = this.modelMapper.map(importShopDTO, Shop.class);
        shop.setTown(this.townRepository.findByName(townByName).get());

//        if (shopRepository.findByName(shop.getName()).isPresent())
//            return "Invalid Town";

        shopRepository.save(shop);
        return "Successfully imported Shop " + shop.getName();
    }
}
