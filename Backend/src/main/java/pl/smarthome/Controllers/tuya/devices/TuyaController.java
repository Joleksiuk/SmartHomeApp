package pl.smarthome.Controllers.tuya.devices;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Controllers.tuya.TuyaService;
import pl.smarthome.Controllers.tuya.details.CodeValue;
import pl.smarthome.Models.Device;
import pl.smarthome.Repositories.DeviceRepository;
import pl.smarthome.Repositories.HouseRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tuya")
public class TuyaController {

    private final DeviceRepository deviceRepository;
    private final HouseRepository houseRepository;
    private final TuyaService tuyaService;

    @GetMapping("verify/{userId}/{deviceId}")
    public Boolean verifyDeviceId(@PathVariable Long userId, @PathVariable String deviceId){

        return tuyaService.verifyDeviceId(userId,deviceId);
    }

    @PostMapping("userId={userId}/device={id}")
    public String getDeviceDetails(@PathVariable Long id,  @PathVariable Long userId){
        Device device = deviceRepository.findById(id).orElse(null);
        Long ownerId = houseRepository.findById(device.getHouseId()).orElse(null).getOwnerId();
        assert device != null;
        return tuyaService.getDeviceDetails(device.getSpecificId(),ownerId).toString();
    }

    @PostMapping("multi/{id}/{userId}")
    public String makeMultiCommands(@RequestBody List<CodeValue> codeValues, @PathVariable Long id, @PathVariable Long userId){
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
