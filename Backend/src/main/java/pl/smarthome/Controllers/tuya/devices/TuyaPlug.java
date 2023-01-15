package pl.smarthome.Controllers.tuya.devices;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Controllers.tuya.TuyaFunctions;
import pl.smarthome.Controllers.tuya.TuyaService;
import pl.smarthome.Controllers.tuya.details.CodeValue;
import pl.smarthome.Models.Device;
import pl.smarthome.Repositories.DeviceRepository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tuya_plug")
public class TuyaPlug {

    private final  TuyaService tuyaService;
    private final DeviceRepository deviceRepository;

    @PostMapping("device={id}/switch={state}/{userId}")
    public String switchPlug (@PathVariable Boolean state, @PathVariable Long id, @PathVariable Long userId) {
        Device device = deviceRepository.findById(id).orElse(null);
        CodeValue codeValue=new CodeValue("switch_1",state.toString());
        List<CodeValue> codeValues=new LinkedList(List.of(codeValue));
        return tuyaService.multiCommandsRequest(codeValues,device.getSpecificId(),userId);
    }

    @PostMapping("multi/{id}/{userId}")
    public String makeMultiCommands(@RequestBody List<CodeValue> codeValues,  @PathVariable Long id, @PathVariable Long userId){
        Device device = deviceRepository.findById(id).orElse(null);
        return tuyaService.multiCommandsRequest(codeValues,device.getSpecificId(),userId);
    }

    @PostMapping("power_consumption/{deviceId}/{userId}")
    public String getPowerConsumption(@PathVariable Long deviceId,@PathVariable Long userId){
        Device device = deviceRepository.findById(deviceId).orElse(null);
        return this.tuyaService.getPowerConsumption(device.getSpecificId(),userId);
    }

}
