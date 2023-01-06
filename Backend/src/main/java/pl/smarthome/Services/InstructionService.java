package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.Instruction;
import pl.smarthome.Models.ids.InstructionId;
import pl.smarthome.Repositories.InstructionRepository;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InstructionService {

    private final InstructionRepository instructionRepository;

    public void createInstruction(Instruction command) {
        instructionRepository.save(command);
    }

    public void updateInstruction(Instruction command) {
        instructionRepository.save(command);
    }

    public List<Instruction> getAllInstructionsBySceneId(Long sceneId){
        return new LinkedList<>();
        //return new LinkedList<>(instructionRepository.getAllBySceneId(sceneId));
    }

    public List<Instruction> getAllInstructionsById(InstructionId id){
        return instructionRepository.getAllById(id);
    }

}