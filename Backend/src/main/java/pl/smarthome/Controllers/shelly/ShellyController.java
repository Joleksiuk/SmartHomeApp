package pl.smarthome.Controllers.shelly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("device={id}/switch={state}")
    public void switchBulb(@PathVariable String id, @PathVariable String state){

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id",id);
        map.add("turn",state);
        shellyService.makeShellyRequest(map,"/device/light/control");
    }
    @GetMapping("device={id}/{command}={value}")
    public void executeCommand(@PathVariable String id, @PathVariable String command, @PathVariable String value){

        shellyService.executeShellyCommand(command,value,id,path);
    }

    @GetMapping("device={id}/brightness={value}")
    public void changeBrightness(@PathVariable String id, @PathVariable String value){

        shellyService.executeShellyCommand("brightness",value,id,path);
    }

    @GetMapping("device={id}/white={value}")
    public void changeWhite(@PathVariable String id, @PathVariable String value){

        shellyService.executeShellyCommand("white",value,id,path);
    }

    @GetMapping("device={id}/temp={value}")
    public void changeTemperature(@PathVariable String id, @PathVariable String value){

        shellyService.executeShellyCommand("temp",value,id,path);
    }

    @GetMapping("device={id}/transition={value}")
    public void changeTransition(@PathVariable String id, @PathVariable String value){

        shellyService.executeShellyCommand("transition",value,id,path);
    }

}
