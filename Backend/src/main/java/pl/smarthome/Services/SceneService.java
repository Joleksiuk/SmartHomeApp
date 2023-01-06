package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.Device;
import pl.smarthome.Models.House;
import pl.smarthome.Models.Instruction;
import pl.smarthome.Models.Scene;
import pl.smarthome.Repositories.DeviceRepository;
import pl.smarthome.Repositories.InstructionRepository;
import pl.smarthome.Repositories.SceneRepository;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SceneService {

    private final SceneRepository sceneRepository;
    private final DeviceRepository deviceRepository;
    private final InstructionRepository instructionRepository;

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
    public List<Optional<Device>> getDevicesBySceneId(Long sceneId){
        List<Instruction> instructions = instructionRepository.getAllBySceneId(sceneId);
        List<Long> uniqueDeviceIds = instructions.stream()
                .map(instruction -> instruction.getId().deviceId).distinct().collect(Collectors.toList());

        return  uniqueDeviceIds.stream().map(deviceRepository::findById).collect(Collectors.toList());

    }
}

