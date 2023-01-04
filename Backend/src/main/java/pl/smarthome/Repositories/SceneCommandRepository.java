package pl.smarthome.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smarthome.Models.SceneCommand;

import java.util.List;

public interface SceneCommandRepository extends JpaRepository<SceneCommand, Long> {
    List<SceneCommand> getAllBySceneId(Long sceneId);
}
