package pl.smarthome.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smarthome.Models.Command;

import java.util.List;
import java.util.Optional;

public interface CommandRepository extends JpaRepository<Command, Long> {

    Optional<Command> findById(Long id);
    List<Command> getAllBySceneId(Long sceneId);
}
