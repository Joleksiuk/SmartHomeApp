package pl.smarthome.Controllers.tuya.devices;

import org.springframework.web.bind.annotation.*;
import pl.smarthome.Controllers.tuya.*;
import pl.smarthome.Controllers.tuya.details.DeviceDetails;
import pl.smarthome.Controllers.tuya.details.Status;
import pl.smarthome.Controllers.tuya.models.HSVColor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("tuya_led")
public class TuyaLED {

    @GetMapping("device={id}/switch={state}")
    public String switchLed (@PathVariable String state, @PathVariable String id) {
        String path = "/v1.0/devices/"+id+"/commands";
        String body = TuyaService.createSwitchBody(Boolean.parseBoolean(state));
        Object result2 = TuyaFunctions.execute(TuyaFunctions.getAccessToken(),path,"POST", body,new HashMap<>() );
        return result2.toString();
    }

    @GetMapping("device={id}/color={hex}")
    public String changeColor (@PathVariable String hex, @PathVariable String id) {
        String path = "/v1.0/devices/"+id+"/commands";
        hex="#"+hex;
        String body = TuyaService.createColorBody(hex);
        Object result2 = TuyaFunctions.execute(TuyaFunctions.getAccessToken(),path,"POST", body,new HashMap<>() );
        return result2.toString();
    }

    @GetMapping("device={id}/intensity={value}")
    public String changeIntensity (@PathVariable String value, @PathVariable String id) {

        HSVColor currentHSV = TuyaService.getCurrentLEDColor(id);
        String body = TuyaService.createIntensityBody(Integer.parseInt(value),currentHSV);
        return  TuyaService.makeRequest(body, "/v1.0/devices/"+id+"/commands");
    }


    @GetMapping("device={id}")
    public String getDeviceDetails(@PathVariable String id){
        return TuyaService.getDeviceDetails(id).toString();
    }

}
