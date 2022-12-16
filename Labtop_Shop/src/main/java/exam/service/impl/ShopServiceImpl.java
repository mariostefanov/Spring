package exam.service.impl;

import exam.model.Shop;
import exam.model.dto.ImportShopDTO;
import exam.model.dto.ImportShopsDTO;
import exam.repository.ShopRepository;
import exam.service.ShopService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
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
    private final JAXBContext context;

    public ShopServiceImpl(ShopRepository shopRepository, Validator validator) throws JAXBException {
        this.shopRepository = shopRepository;
        this.modelMapper = new ModelMapper();
        this.validator = validator;
        this.context = JAXBContext.newInstance(ImportShopsDTO.class);
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
        ImportShopsDTO shops = (ImportShopsDTO) unmarshaller.unmarshal(new FileReader(xmlPath.toAbsolutePath().toString()));

        shops.getShops().stream().map(this::importShop);
        return null;
    }

    private String importShop(ImportShopDTO importShopDTO) {
        Set<ConstraintViolation<ImportShopDTO>> violations =
                validator.validate(importShopDTO);

        if (!violations.isEmpty()){
            return "Invalid Shop";
        }

        Optional<Shop> shopOpt = this.shopRepository
                .findByAddressAndEmployeeCountAndIncomeAndNameAndShopAreaAndTownName(
                        importShopDTO.getAddress(),
                        importShopDTO.getEmployeeCount(),
                        importShopDTO.getIncome(),
                        importShopDTO.getName(),
                        importShopDTO.getShopAria(),
                        importShopDTO.getTownName()
                        );

        if (shopOpt.isPresent()){
            return "Invalid Shop";
        }

        Shop shop = this.modelMapper.map(importShopDTO, Shop.class);

        shopRepository.save(shop);

        return "Successfully imported Shop " + shop;
    }
}
