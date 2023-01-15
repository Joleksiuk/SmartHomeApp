package pl.smarthome.Controllers.shelly;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Controllers.tuya.details.CodeValue;
import pl.smarthome.Models.Device;
import pl.smarthome.Repositories.DeviceRepository;

import java.util.List;

@RestController
@RequestMapping("shelly")
@RequiredArgsConstructor
public class ShellyController {

    private final ShellyService shellyService;
    private final DeviceRepository deviceRepository;

    private static String path = "/device/light/control";

    //e8db84d500b1
    @PostMapping("device={id}/switch={state}/{userId}")
    public String switchBulb(@PathVariable Long id, @PathVariable String state, @PathVariable Long userId){
        Device device = deviceRepository.findById(id).orElse(null);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id",device.getSpecificId());
        map.add("turn",state);
        return shellyService.makeShellyRequest(map,"/device/light/control", userId);
    }
    @PostMapping("device={id}/{command}={value}/{userId}")
    public String executeCommand(@PathVariable Long id, @PathVariable String command, @PathVariable String value, @PathVariable Long userId){
        Device device = deviceRepository.findById(id).orElse(null);
        return shellyService.executeShellyCommand(command,value,device.getSpecificId(),path,userId);
    }

    @PostMapping("device={id}/brightness={value}/{userId}")
    public String changeBrightness(@PathVariable Long id, @PathVariable String value, @PathVariable Long userId){
        Device device = deviceRepository.findById(id).orElse(null);
        return shellyService.executeShellyCommand("brightness",value,device.getSpecificId(),path,userId);
    }

    @PostMapping("device={id}/white={value}/{userId}")
    public String changeWhite(@PathVariable Long id, @PathVariable String value,@PathVariable Long userId){
        Device device = deviceRepository.findById(id).orElse(null);
        return shellyService.executeShellyCommand("white",value,device.getSpecificId(),path,userId);
    }

    @PostMapping("device={id}/temp={value}/{userId}")
    public String changeTemperature(@PathVariable Long id, @PathVariable String value,@PathVariable Long userId){
        Device device = deviceRepository.findById(id).orElse(null);
        return shellyService.executeShellyCommand("temp",value,device.getSpecificId(),path,userId);
    }

    @PostMapping("device={id}/transition={value}/{userId}")
    public String changeTransition(@PathVariable Long id, @PathVariable String value,@PathVariable Long userId){
        Device device = deviceRepository.findById(id).orElse(null);
        return shellyService.executeShellyCommand("transition",value,device.getSpecificId(),path,userId);
    }

    @GetMapping("{userId}/{deviceId}/Status")
    public List<CodeValue> getDeviceStatus(@PathVariable Long deviceId,@PathVariable Long userId){
        Device device = deviceRepository.findById(deviceId).orElse(null);
        return shellyService.getStatus(device.getSpecificId(),userId);
    }

    @GetMapping("device={id}/settings/{userId}")
    public String getSettings(@PathVariable Long id,@PathVariable Long userId){
        Device device = deviceRepository.findById(id).orElse(null);
        return shellyService.executeShellyCommand("","",device.getSpecificId(),"/device/settings",userId);
    }

    @PostMapping("multi/{id}/{userId}")
    public String makeMultiRequest(@RequestBody List<CodeValue> codeValues, @PathVariable Long id,@PathVariable Long userId){
        Device device = deviceRepository.findById(id).orElse(null);
        return shellyService.multiControl(codeValues,device.getSpecificId(),path,userId);
    }


}
