package pl.smarthome.Controllers.tuya.devices;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Controllers.tuya.*;
import pl.smarthome.Controllers.tuya.details.CodeValue;
import pl.smarthome.Controllers.tuya.details.Secret;
import pl.smarthome.Models.Device;
import pl.smarthome.Repositories.DeviceRepository;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("tuya_led")
@RequiredArgsConstructor
public class TuyaLED {

    private final  TuyaService tuyaService;
    private final DeviceRepository deviceRepository;

    @PostMapping("userId={userId}/device={id}/switch={state}")
    public String switchLed (@PathVariable Boolean state, @PathVariable Long id, @PathVariable Long userId) {
        Device device = deviceRepository.findById(id).orElse(null);
        CodeValue codeValue=new CodeValue("switch_led",state.toString());
        List<CodeValue> codeValues=new LinkedList(List.of(codeValue));
        return tuyaService.multiCommandsRequest(codeValues,device.getSpecificId(), userId);
    }

    @PostMapping("userId={userId}/device={id}/color={hex}")
    public String changeColor (@PathVariable String hex, @PathVariable Long id,  @PathVariable Long userId) {
        CodeValue codeValue=new CodeValue("colour_data","#"+hex);
        Device device = deviceRepository.findById(id).orElse(null);
        List<CodeValue> codeValues=new LinkedList(List.of(codeValue));
        return tuyaService.multiCommandsRequest(codeValues,device.getSpecificId(),userId);
    }

    @PostMapping("userId={userId}/device={id}/intensity={value}")
    public String changeIntensity (@PathVariable String value, @PathVariable Long id,  @PathVariable Long userId) {

        Device device = deviceRepository.findById(id).orElse(null);
        CodeValue codeValue=new CodeValue("intensity",value);
        List<CodeValue> codeValues=new LinkedList(List.of(codeValue));
        return tuyaService.multiCommandsRequest(codeValues,device.getSpecificId(), userId);
    }

    @PostMapping("userId={userId}/device={id}")
    public String getDeviceDetails(@PathVariable Long id,  @PathVariable Long userId){
        Device device = deviceRepository.findById(id).orElse(null);
        assert device != null;
        return tuyaService.getDeviceDetails(device.getSpecificId(),userId).toString();
    }

    @PostMapping("multi/{id}/{userId}")
    public String makeMultiCommands(@RequestBody List<CodeValue> codeValues,  @PathVariable Long id, @PathVariable Long userId){
        Device device = deviceRepository.findById(id).orElse(null);
        assert device != null;
        return tuyaService.multiCommandsRequest(codeValues,device.getSpecificId(),userId);
    }

    @GetMapping("{userId}/{deviceId}/Status")
    public List<CodeValue> getDeviceStatus(@PathVariable Long deviceId,  @PathVariable Long userId){

        return tuyaService.getDeviceStatus(deviceId,userId);
    }

}
