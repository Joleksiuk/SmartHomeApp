package pl.smarthome.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smarthome.Models.Instruction;

import java.util.List;
import java.util.Optional;

public interface InstructionRepository extends JpaRepository<Instruction, Long> {
    List<Instruction> getAllBySceneId(Long sceneId);
}
