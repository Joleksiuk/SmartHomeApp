package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Controllers.shelly.ShellyService;
import pl.smarthome.Controllers.tuya.TuyaService;
import pl.smarthome.Controllers.tuya.details.CodeValue;
import pl.smarthome.Models.*;
import pl.smarthome.Models.dtos.CommandDto;
import pl.smarthome.Models.dtos.DeviceDto;
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

    private final ParameterService parameterService;

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


    public DeviceDto devicetoDto(Device device){
        Component component = componentRepository.findById(device.getComponentId()).orElse(null);
        return new DeviceDto(device,component.getName(),component.getImagePath(),
                component.getBrand(),getDefaultComponentProps(device.getComponentId()));
    }

    public DeviceDto devicetoDto(Device device, Long sceneId){
        Component component = componentRepository.findById(device.getComponentId()).orElse(null);
        return new DeviceDto(device,component.getName(),component.getImagePath(),
                component.getBrand(),getDeviceSceneProps(sceneId,device.getId()));
    }
    public List<DeviceDto> getDevicesBySceneId(Long sceneId){
        List<Command> commands = commandRepository.getAllBySceneId(sceneId);
        List<Long> uniqueDeviceIds = commands.stream()
                .map(Command::getDeviceId).distinct().collect(Collectors.toList());

        List<Device> devices = uniqueDeviceIds.stream()
                .map(deviceRepository::findById)
                .map(device -> device.orElse(null))
                .collect(Collectors.toList());

        return devices.stream().map(device -> {
            return devicetoDto(device,sceneId);
        } ).toList();
    }

    public List<DeviceDto> getHouseDevicesToAddBySceneId(Long houseId, Long sceneId){
        List<Device> homeDevices = deviceRepository.getAllByHouseId(houseId);
        List<DeviceDto> houseDevices =  new ArrayList<> (homeDevices.stream().map(device -> {
            return devicetoDto(device,sceneId);
        } ).toList());
        List<DeviceDto> sceneDevices = new ArrayList<> (getDevicesBySceneId(sceneId));

        for(DeviceDto device: houseDevices){
            for(DeviceDto d:sceneDevices){
                if(device.getId()==d.getId()){
                    houseDevices.remove(device);
                }
            }
        }
        return  houseDevices;
    }

    public void addDeviceToScene(Long deviceId, Long sceneId){
        Device device =  deviceRepository.findById(deviceId).orElse(null);
        if(device==null){
            return;
        }
        List<Parameter> params = parameterRepository.findAllByComponentId(device.getComponentId());
        for(Parameter param:params){
            Command command =new Command(param.getCode(),param.getValue(),deviceId,sceneId);
            commandService.createCommand(command);
        }
    }

    public void setScene(Long sceneId, Long userId){
        List<DeviceDto> devices = getDevicesBySceneId(sceneId);
        for(DeviceDto device: devices){
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
    public List<CodeValue> getDeviceSceneProps(Long sceneId, Long deviceId){
        return commandService.getAllCommandsBySceneId(sceneId).stream()
                .filter(command -> {return command.getDeviceId()==deviceId;})
                .map(command -> {return new CodeValue(command.getCode(),command.getValue());}).toList();
    }

    public List<CodeValue> getDefaultComponentProps(Long componentId){

        List<CodeValue> cvs = parameterService.getAllByComponentId(componentId)
                .stream().map(parameter -> {
                    return new CodeValue(parameter.getCode(),parameter.getValue());})
                .toList();
        return cvs;
    }
}

