package pl.smarthome.Controllers.tuya.devices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Controllers.tuya.TuyaFunctions;
import pl.smarthome.Controllers.tuya.TuyaService;
import pl.smarthome.Controllers.tuya.details.CodeValue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("tuya_plug")
public class TuyaPlug {

    private final  TuyaService tuyaService;

    @Autowired
    public TuyaPlug(TuyaService tuyaService) {
        this.tuyaService = tuyaService;
    }

    @PostMapping("device={id}/switch={state}")
    public String switchPlug (@PathVariable Boolean state, @PathVariable String id, @RequestBody Long userId) {
        CodeValue codeValue=new CodeValue("switch_1",state.toString());
        List<CodeValue> codeValues=new LinkedList(List.of(codeValue));
        return tuyaService.multiCommandsRequest(codeValues,id,userId);
    }

    @PostMapping("multi/{id}/{userId}")
    public String makeMultiCommands(@RequestBody List<CodeValue> codeValues,  @PathVariable String id, @PathVariable Long userId){
        return tuyaService.multiCommandsRequest(codeValues,id,userId);
    }

    @PostMapping("power_consumption/{deviceId}/{userId}")
    public String getPowerConsumption(@PathVariable String deviceId,@PathVariable Long userId){

        return this.tuyaService.getPowerConsumption(deviceId,userId);
    }

}
