package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.Command;
import pl.smarthome.Models.Device;
import pl.smarthome.Models.Scene;
import pl.smarthome.Repositories.CommandRepository;
import pl.smarthome.Repositories.DeviceRepository;
import pl.smarthome.Repositories.SceneRepository;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SceneService {

    private final SceneRepository sceneRepository;
    private final DeviceRepository deviceRepository;
    private final CommandRepository commandRepository;

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


    public List<Device> getDevicesBySceneId(Long sceneId){
        List<Command> commands = commandRepository.getAllBySceneId(sceneId);
        List<Long> uniqueDeviceIds = commands.stream()
                .map(Command::getDeviceId).distinct().collect(Collectors.toList());

        return  uniqueDeviceIds.stream()
                .map(deviceRepository::findById)
                .map(device -> device.orElse(null))
                .collect(Collectors.toList());
    }

    public List<Device> getHouseDevicesToAddBySceneId(Long houseId, Long sceneId){
        List<Device> houseDevices = deviceRepository.getAllByHouseId(houseId);
        List<Device> sceneDevices = getDevicesBySceneId(sceneId);
        houseDevices.removeAll(sceneDevices);
        return  houseDevices;
    }

    public void addDeviceToScene(Long deviceId, Long sceneId){

    }
}

