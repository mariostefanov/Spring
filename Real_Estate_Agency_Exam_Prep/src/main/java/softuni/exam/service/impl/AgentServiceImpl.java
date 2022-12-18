package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportAgentDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Service
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;
    private final Path agentPath;

    private final ModelMapper modelMapper;

    private final Gson gson;

    private final Validator validator;
    private final TownRepository townRepository;


    public AgentServiceImpl(AgentRepository agentRepository, Validator validator, TownRepository townRepository) {
        this.agentRepository = agentRepository;
        this.validator = validator;
        this.townRepository = townRepository;
        this.agentPath = Path.of("src/main/resources/files/json/agents.json");
        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder().create();

    }

    @Override
    public boolean areImported() {
        return this.agentRepository.count()>0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return String.join("\n",Files.readAllLines(agentPath));
    }

    @Override
    public String importAgents() throws IOException {
        String json = this.readAgentsFromFile();

        ArrayList<String> result = new ArrayList<>();
        ImportAgentDTO[] importAgentDTOS = gson.fromJson(json, ImportAgentDTO[].class);

        for (ImportAgentDTO importAgentDTO : importAgentDTOS) {
            Set<ConstraintViolation<ImportAgentDTO>> violations =
                    validator.validate(importAgentDTO);

            if (violations.isEmpty()){
                Optional<Town> townOpt =
                        this.townRepository.findByTownName(importAgentDTO.getTownName());

                if (townOpt.isPresent()){
                    Optional<Agent> agentOpt = this.agentRepository.findByFirstName(importAgentDTO.getFirstName());
                    if (agentOpt.isEmpty()){
                        Agent agent = this.modelMapper.map(importAgentDTO, Agent.class);
                        agent.setTown(townOpt.get());
                        this.agentRepository.save(agent);
                        result.add("Successfully imported agent " + agent.getFirstName() + " " + agent.getLastName());
                        continue;
                    }
                }
            }
            result.add("Invalid agent");
        }
        return String.join("\n", result);
    }
}
