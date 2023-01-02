package pl.smarthome.Controllers.tuya;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import pl.smarthome.Controllers.tuya.details.DeviceDetails;
import pl.smarthome.Controllers.tuya.details.Status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

class TuyaServiceTest {

    @Test
    void getDeviceDetails() {
        String path = "/v1.0/devices/bf2b8148e20535ca2eaik5";
        Object result = TuyaFunctions.execute(TuyaFunctions.getAccessToken(),path,"GET", "",new HashMap<>() );
        Gson gson=new Gson();
        System.out.println(gson.fromJson(gson.toJson(result), DeviceDetails.class));
    }

    @Test
    void getLEDColor(){
        DeviceDetails deviceDetails = TuyaService.getDeviceDetails("bf2b8148e20535ca2eaik5");
        List<Status> statuses = Arrays.stream(deviceDetails.result.getStatus()).toList();
        for(Status status: statuses){
            if(Objects.equals(status.getCode(), "colour_data")){
                System.out.println(HSVColor.JsonToHSV(status.getValue().toString()));

                break;
            }
        }

    }
}