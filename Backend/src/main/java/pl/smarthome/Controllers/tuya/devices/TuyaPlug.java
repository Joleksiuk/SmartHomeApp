package pl.smarthome.Controllers.tuya.devices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.smarthome.Controllers.tuya.TuyaFunctions;
import pl.smarthome.Controllers.tuya.TuyaService;

import java.util.HashMap;

@RestController
@RequestMapping("tuya_plug")
public class TuyaPlug {

    @GetMapping("device={id}/switch={state}")
    public String switchLed (@PathVariable Boolean state, @PathVariable String id) {
        String path = "/v1.0/devices/"+id+"/commands";
        String body = TuyaService.plugSwitch(state);
        Object result2 = TuyaFunctions.execute(TuyaFunctions.getAccessToken(),path,"POST", body,new HashMap<>() );
        return result2.toString();
    }
}
