package pl.smarthome.Controllers.tuya.devices;

import org.springframework.web.bind.annotation.*;
import pl.smarthome.Controllers.tuya.*;
import pl.smarthome.Controllers.tuya.details.DeviceDetails;
import pl.smarthome.Controllers.tuya.details.Status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("tuya_led")
public class TuyaLED {

    @GetMapping("device={id}/switch={state}")
    public String switchLed (@PathVariable Boolean state, @PathVariable String id) {
        String path = "/v1.0/devices/"+id+"/commands";
        String body = TuyaService.ledSwitch(state);
        Object result2 = TuyaFunctions.execute(TuyaFunctions.getAccessToken(),path,"POST", body,new HashMap<>() );
        return result2.toString();
    }

    @GetMapping("device={id}/color={hex}")
    public String changeColor (@PathVariable String hex, @PathVariable String id) {
        String path = "/v1.0/devices/"+id+"/commands";
        hex="#"+hex;
        String body = TuyaService.changeColor(hex);
        Object result2 = TuyaFunctions.execute(TuyaFunctions.getAccessToken(),path,"POST", body,new HashMap<>() );
        return result2.toString();
    }

    @GetMapping("device={id}/intensity={value}")
    public String changeIntensity (@PathVariable Integer value, @PathVariable String id) {
        DeviceDetails deviceDetails = TuyaService.getDeviceDetails(id);
        HSVColor currentHSV=new HSVColor();
        List<Status> statuses = Arrays.stream(deviceDetails.result.getStatus()).toList();
        for(Status status: statuses){
            if(Objects.equals(status.getCode(), "colour_data")){
                currentHSV = HSVColor.JsonToHSV(status.getValue().toString());
                break;
            }
        }
        String path = "/v1.0/devices/"+id+"/commands";
        String body = TuyaService.changeIntensity(value,currentHSV);
        Object result2 = TuyaFunctions.execute(TuyaFunctions.getAccessToken(),path,"POST", body,new HashMap<>() );
        return result2.toString();
    }

    @GetMapping("device={id}")
    public String getDeviceDetails(@PathVariable String id){
        return TuyaService.getDeviceDetails(id).toString();
    }

}
