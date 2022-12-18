package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportTownDTO;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;

    private final ModelMapper modelMapper;

    private final Gson gson;

    private final Path townPath;
    private final Validator validator;

    public TownServiceImpl(TownRepository townRepository, Validator validator) {
        this.townRepository = townRepository;
        this.modelMapper = new ModelMapper();
        this.validator = validator;
        this.townPath = Path.of("src/main/resources/files/json/towns.json");
        this.gson = new GsonBuilder().create();

    }

    @Override
    public boolean areImported() {
        return this.townRepository.count()>0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return String.join("\n", Files.readAllLines(townPath));
    }

    @Override
    public String importTowns() throws IOException {
        String json = this.readTownsFileContent();
        List<String> result =new ArrayList<>();
        ImportTownDTO[] importTownDTOS = gson.fromJson(json, ImportTownDTO[].class);

        for (ImportTownDTO importTownDTO : importTownDTOS) {
            Set<ConstraintViolation<ImportTownDTO>> violations =
                    this.validator.validate(importTownDTO);
            if (violations.isEmpty()){
                Optional<Town> townOpt = this.townRepository.findByTownName(importTownDTO.getTownName());
                if (townOpt.isEmpty()){
                    Town town = this.modelMapper.map(importTownDTO, Town.class);
                    this.townRepository.save(town);
                    result.add("Successfully imported town " + town.getTownName() + " " + town.getPopulation());
                    continue;
                }
             }
            result.add("Invalid town");
        }
        return String.join("\n", result);
    }
}
