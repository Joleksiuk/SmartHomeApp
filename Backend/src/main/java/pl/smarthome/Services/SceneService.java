package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Controllers.shelly.ShellyService;
import pl.smarthome.Controllers.tuya.TuyaService;
import pl.smarthome.Controllers.tuya.details.CodeValue;
import pl.smarthome.Controllers.tuya.devices.TuyaLED;
import pl.smarthome.Controllers.tuya.devices.TuyaPlug;
import pl.smarthome.Models.*;
import pl.smarthome.Repositories.*;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SceneService {

    private final SceneRepository sceneRepository;
    private final DeviceRepository deviceRepository;
    private final CommandRepository commandRepository;
    private final ParameterRepository parameterRepository;

    private final CommandService commandService;
    private final ComponentRepository componentRepository;

    private final TuyaService tuyaService;
    private final ShellyService shellyService;


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
        Device device =  deviceRepository.findById(deviceId).orElse(null);
        if(device==null){
            return;
        }
        List<Parameter> params = parameterRepository.findAllByComponentId(device.getComponentId());
        for(Parameter param:params){
            commandRepository.save(new Command(param.getCode(),param.getValue(),deviceId,sceneId));
        }

    }

    public void setScene(Long sceneId, Long userId){
        List<Device> devices = getDevicesBySceneId(sceneId);
        for(Device device: devices){
            List<CodeValue> cvs = new LinkedList<>();
            List <CommandDto> list =  commandService.getAllCommandsDtoByIds(sceneId,device.getId());
            for(CommandDto c:list){
                cvs.add(new CodeValue(c.getCode(),c.getValue()));
            }

            Component component =componentRepository.findById(device.getComponentId()).orElse(null);
            switch (component.getBrand()) {
                case "TUYA" -> tuyaService.multiCommandsRequest(cvs, device.getSpecificId(), userId);
                case "Shelly" -> shellyService.multiControl(cvs, device.getSpecificId(), "/device/light/control", userId);
            }
        }
    }
}

