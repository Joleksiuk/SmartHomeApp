package pl.smarthome.Controllers.tuya.devices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Controllers.tuya.*;
import pl.smarthome.Controllers.tuya.details.CodeValue;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("tuya_led")
public class TuyaLED {

    private final  TuyaService tuyaService;

    @Autowired
    public TuyaLED(TuyaService tuyaService) {
        this.tuyaService = tuyaService;
    }

    @PostMapping("device={id}/switch={state}")
    public String switchLed (@PathVariable Boolean state, @PathVariable String id) {
        CodeValue codeValue=new CodeValue("switch_led",state);
        List<CodeValue> codeValues=new LinkedList(List.of(codeValue));
        return tuyaService.multiCommandsRequest(codeValues,id);
    }

    @PostMapping("device={id}/color={hex}")
    public String changeColor (@PathVariable String hex, @PathVariable String id) {
        CodeValue codeValue=new CodeValue("colour_data","#"+hex);
        List<CodeValue> codeValues=new LinkedList(List.of(codeValue));
        return tuyaService.multiCommandsRequest(codeValues,id);
    }

    @PostMapping("device={id}/intensity={value}")
    public String changeIntensity (@PathVariable String value, @PathVariable String id) {

        CodeValue codeValue=new CodeValue("colour_data",value);
        List<CodeValue> codeValues=new LinkedList(List.of(codeValue));
        return tuyaService.multiCommandsRequest(codeValues,id);
    }

    @PostMapping("device={id}")
    public String getDeviceDetails(@PathVariable String id){
        return tuyaService.getDeviceDetails(id).toString();
    }

    @PostMapping("multi/{id}")
    public String makeMultiCommands(@RequestBody List<CodeValue> codeValues,  @PathVariable String id){
        return tuyaService.multiCommandsRequest(codeValues,id);
    }
}
