package pl.smarthome.Controllers.shelly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Controllers.tuya.details.CodeValue;

import java.util.List;

@RestController
@RequestMapping("shelly")
public class ShellyController {

    private final ShellyService shellyService;
    private static String path = "/device/light/control";
    @Autowired
    public ShellyController(ShellyService shellyService) {
        this.shellyService = shellyService;
    }

    //e8db84d500b1
    @PostMapping("device={id}/switch={state}")
    public String switchBulb(@PathVariable String id, @PathVariable String state, @RequestBody Long userId){

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id",id);
        map.add("turn",state);
        return shellyService.makeShellyRequest(map,"/device/light/control", userId);
    }
    @PostMapping("device={id}/{command}={value}")
    public String executeCommand(@PathVariable String id, @PathVariable String command, @PathVariable String value, @RequestBody Long userId){

        return shellyService.executeShellyCommand(command,value,id,path,userId);
    }

    @PostMapping("device={id}/brightness={value}")
    public String changeBrightness(@PathVariable String id, @PathVariable String value, @RequestBody Long userId){

        return shellyService.executeShellyCommand("brightness",value,id,path,userId);
    }

    @PostMapping("device={id}/white={value}")
    public String changeWhite(@PathVariable String id, @PathVariable String value,@RequestBody Long userId){

        return shellyService.executeShellyCommand("white",value,id,path,userId);
    }

    @PostMapping("device={id}/temp={value}")
    public String changeTemperature(@PathVariable String id, @PathVariable String value,@RequestBody Long userId){

        return shellyService.executeShellyCommand("temp",value,id,path,userId);
    }

    @PostMapping("device={id}/transition={value}")
    public String changeTransition(@PathVariable String id, @PathVariable String value,@RequestBody Long userId){

        return shellyService.executeShellyCommand("transition",value,id,path,userId);
    }

    @PostMapping("{id}")
    public String checkState(@PathVariable String id,@RequestBody Long userId){

        return shellyService.executeShellyCommand("","",id,"/device/status",userId);
    }

    @GetMapping("device={id}/settings")
    public String getSettings(@PathVariable String id,@RequestBody Long userId){

        return shellyService.executeShellyCommand("","",id,"/device/settings",userId);
    }

    @PostMapping("multi/{id}/{userId}")
    public String makeMultiRequest(@RequestBody List<CodeValue> codeValues, @PathVariable String id,@PathVariable Long userId){
        return shellyService.multiControl(codeValues,id,path,userId);
    }


}
