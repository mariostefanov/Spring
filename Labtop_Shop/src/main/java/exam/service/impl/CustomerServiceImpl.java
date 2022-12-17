package exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exam.model.Customer;
import exam.model.Town;
import exam.model.dto.ImportCustomerDto;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final Path jsonPath = Path.of("src/main/resources/files/json/customers.json");
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final TownRepository townRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, TownRepository townRepository) {
        this.customerRepository = customerRepository;
        this.townRepository = townRepository;
        this.gson = new GsonBuilder().create();
        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.modelMapper.addConverter(ctx -> LocalDate.parse(ctx.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                String.class, LocalDate.class);
    }

    @Override
    public boolean areImported() {
        return this.customerRepository.count()>0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(jsonPath);
    }

    @Override
    public String importCustomers() throws IOException {
        String json = this.readCustomersFileContent();

        ImportCustomerDto[] importCustomerDtos = this.gson.fromJson(json, ImportCustomerDto[].class);

        List<String> result = new ArrayList<>();

        for (ImportCustomerDto importCustomerDto : importCustomerDtos) {
            Set<ConstraintViolation<ImportCustomerDto>> violations = this.validator.validate(importCustomerDto);

            if (violations.isEmpty()){
                Optional<Town> townOpt = this.townRepository.findByName(importCustomerDto.getTown().getName());
                if (townOpt.isPresent()){
                    Optional< Customer> customerOpt = this.customerRepository.findByEmail(importCustomerDto.getEmail());
                    if (customerOpt.isEmpty()){
                        Customer customer = modelMapper.map(importCustomerDto, Customer.class);
                        customer.setTown(townOpt.get());
                        this.customerRepository.save(customer);
                        result.add("Successfully imported Customer " + customer.getFirstName() + " " + customer.getLastName());
                        continue;
                    }
                }
            }
            result.add("Invalid Customer");
        }
        return String.join("\n",result);
    }
}
