package pl.smarthome.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smarthome.Models.Instruction;
import pl.smarthome.Models.ids.InstructionId;

import java.util.List;


public interface InstructionRepository extends JpaRepository<Instruction, InstructionId> {
    //List<Instruction> getAllBySceneId(Long sceneId);
    List<Instruction> getAllById(InstructionId instructionId);
}
