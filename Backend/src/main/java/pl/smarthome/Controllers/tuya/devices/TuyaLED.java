package pl.smarthome.Controllers.tuya.devices;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Controllers.tuya.*;
import pl.smarthome.Controllers.tuya.details.CodeValue;
import pl.smarthome.Controllers.tuya.details.Secret;
import pl.smarthome.Models.Device;
import pl.smarthome.Repositories.DeviceRepository;
import pl.smarthome.Repositories.HouseRepository;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("tuya_led")
@RequiredArgsConstructor
public class TuyaLED {

    private final  TuyaService tuyaService;
    private final DeviceRepository deviceRepository;
    private final HouseRepository houseRepository;

    @PostMapping("userId={userId}/device={id}/switch={state}")
    public String switchLed (@PathVariable Boolean state, @PathVariable Long id, @PathVariable Long userId) {
        Device device = deviceRepository.findById(id).orElse(null);
        Long ownerId = houseRepository.findById(device.getHouseId()).orElse(null).getOwnerId();
        CodeValue codeValue=new CodeValue("switch_led",state.toString());
        List<CodeValue> codeValues=new LinkedList(List.of(codeValue));
        return tuyaService.multiCommandsRequest(codeValues,device.getSpecificId(), ownerId);
    }

    @PostMapping("userId={userId}/device={id}/color={hex}")
    public String changeColor (@PathVariable String hex, @PathVariable Long id,  @PathVariable Long userId) {
        CodeValue codeValue=new CodeValue("colour_data","#"+hex);
        Device device = deviceRepository.findById(id).orElse(null);
        Long ownerId = houseRepository.findById(device.getHouseId()).orElse(null).getOwnerId();
        List<CodeValue> codeValues=new LinkedList(List.of(codeValue));
        return tuyaService.multiCommandsRequest(codeValues,device.getSpecificId(),ownerId);
    }

    @PostMapping("userId={userId}/device={id}/intensity={value}")
    public String changeIntensity (@PathVariable String value, @PathVariable Long id,  @PathVariable Long userId) {

        Device device = deviceRepository.findById(id).orElse(null);
        Long ownerId = houseRepository.findById(device.getHouseId()).orElse(null).getOwnerId();
        CodeValue codeValue=new CodeValue("intensity",value);
        List<CodeValue> codeValues=new LinkedList(List.of(codeValue));
        return tuyaService.multiCommandsRequest(codeValues,device.getSpecificId(), ownerId);
    }

    @PostMapping("userId={userId}/device={id}")
    public String getDeviceDetails(@PathVariable Long id,  @PathVariable Long userId){
        Device device = deviceRepository.findById(id).orElse(null);
        Long ownerId = houseRepository.findById(device.getHouseId()).orElse(null).getOwnerId();
        assert device != null;
        return tuyaService.getDeviceDetails(device.getSpecificId(),ownerId).toString();
    }

    @PostMapping("multi/{id}/{userId}")
    public String makeMultiCommands(@RequestBody List<CodeValue> codeValues,  @PathVariable Long id, @PathVariable Long userId){
        Device device = deviceRepository.findById(id).orElse(null);
        Long ownerId = houseRepository.findById(device.getHouseId()).orElse(null).getOwnerId();
        assert device != null;
        return tuyaService.multiCommandsRequest(codeValues,device.getSpecificId(),ownerId);
    }

    @GetMapping("{userId}/{deviceId}/Status")
    public List<CodeValue> getDeviceStatus(@PathVariable Long deviceId,  @PathVariable Long userId){
        Device device = deviceRepository.findById(deviceId).orElse(null);
        Long ownerId = houseRepository.findById(device.getHouseId()).orElse(null).getOwnerId();
        return tuyaService.getDeviceStatus(deviceId,ownerId);
    }

}
