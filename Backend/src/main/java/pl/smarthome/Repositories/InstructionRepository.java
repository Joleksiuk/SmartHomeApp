package pl.smarthome.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smarthome.Models.Instruction;
import pl.smarthome.Models.InstructionId;

import java.util.List;


public interface InstructionRepository extends JpaRepository<Instruction, Long> {
    //List<Instruction> getAllBySceneId(Long sceneId);
    List<Instruction> getAllById(InstructionId instructionId);
}
