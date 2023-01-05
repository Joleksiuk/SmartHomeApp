package pl.smarthome.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smarthome.Models.Scene;

import java.util.List;
import java.util.Optional;

public interface SceneRepository extends JpaRepository<Scene, Long> {
    Optional<Scene> findById(Long id);
    List<Scene> getAllByOwnerId(Long id);
    List<Scene> getAllByHouseId(Long id);
}
