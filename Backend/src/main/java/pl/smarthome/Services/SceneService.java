package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.House;
import pl.smarthome.Models.Scene;
import pl.smarthome.Repositories.SceneRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SceneService {

    private final SceneRepository sceneRepository;

    public void createScene(Scene scene) {
        sceneRepository.save( scene);
    }

    public void updateScene(Scene scene) {
        sceneRepository.save( scene);
    }

    public void deleteSceneById(Long id){
        sceneRepository.deleteById(id);
    }

    public Optional<Scene> findSceneBySceneId(Long houseId) {
        return sceneRepository.findById(houseId);
    }

    public List<Scene> getScenesByOwnerId(Long ownerId) {
        return sceneRepository.getAllByOwnerId(ownerId);
    }
    public List<Scene> getScenesByHouseId(Long houseId) {
        return sceneRepository.getAllByHouseId(houseId);
    }
}

