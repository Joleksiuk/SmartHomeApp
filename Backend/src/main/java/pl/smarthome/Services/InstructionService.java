package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.smarthome.Models.Instruction;
import pl.smarthome.Repositories.InstructionRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InstructionService {

    private final InstructionRepository instructionRepository;
    private final RestTemplate restTemplate;


    public void createInstruction(Instruction command) {
        instructionRepository.save(command);
    }

    public void updateInstruction(Instruction command) {
        instructionRepository.save(command);
    }

    public Optional<Instruction> findInstructionbyId(Long id) {
        return instructionRepository.findById(id);
    }

    public List<Instruction> getAllInstructionsBySceneId(Long sceneId){
        return new LinkedList<>(instructionRepository.getAllBySceneId(sceneId));
    }

    public void executeScene(Long sceneId){

        List<Instruction> instructions =  getAllInstructionsBySceneId(sceneId);
        instructions.stream()
                .map(Instruction::getEndpoint)
                .forEach(this::executeRequest);

    }

    public void executeRequest(String path){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(path, HttpMethod.POST, entity, Object.class);
    }
}