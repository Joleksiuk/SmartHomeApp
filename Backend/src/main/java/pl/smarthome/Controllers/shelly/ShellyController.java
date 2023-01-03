package pl.smarthome.Controllers.shelly;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    public String switchBulb(@PathVariable String id, @PathVariable String state){

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id",id);
        map.add("turn",state);
        return shellyService.makeShellyRequest(map,"/device/light/control");
    }
    @GetMapping("device={id}/{command}={value}")
    public String executeCommand(@PathVariable String id, @PathVariable String command, @PathVariable String value){

        return shellyService.executeShellyCommand(command,value,id,path);
    }

    @GetMapping("device={id}/brightness={value}")
    public String changeBrightness(@PathVariable String id, @PathVariable String value){

        return shellyService.executeShellyCommand("brightness",value,id,path);
    }

    @GetMapping("device={id}/white={value}")
    public String changeWhite(@PathVariable String id, @PathVariable String value){

        return shellyService.executeShellyCommand("white",value,id,path);
    }

    @GetMapping("device={id}/temp={value}")
    public String changeTemperature(@PathVariable String id, @PathVariable String value){

        return shellyService.executeShellyCommand("temp",value,id,path);
    }

    @GetMapping("device={id}/transition={value}")
    public String changeTransition(@PathVariable String id, @PathVariable String value){

        return shellyService.executeShellyCommand("transition",value,id,path);
    }

    @GetMapping("{id}")
    public String checkState(@PathVariable String id){

        return shellyService.executeShellyCommand("","",id,"/device/status");
    }

    @GetMapping("device={id}/settings")
    public String getSettings(@PathVariable String id){

        String response = shellyService.executeShellyCommand("","",id,"/device/settings");
        return response;
    }

}
