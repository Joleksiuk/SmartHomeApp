package pl.smarthome.Controllers.tuya;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import pl.smarthome.Controllers.tuya.details.DeviceDetails;
import pl.smarthome.Controllers.tuya.details.Status;
import pl.smarthome.Controllers.tuya.models.CodeValueString;
import pl.smarthome.Controllers.tuya.models.HSVColor;

import java.util.*;

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

    @Test
    void testMultiRequestBody(){

        String id="1";
        List<CodeValueString> codeValueList=new LinkedList<>();

        codeValueList.add(new CodeValueString("switch_led","true"));
        codeValueList.add(new CodeValueString("switch_led","false"));
        Gson gson=new Gson();

        for(CodeValueString cv: codeValueList){
            switch (cv.getCode()) {
                case "switch_led" -> cv.setValue(TuyaService.createSwitchBody(Boolean.parseBoolean(cv.getValue())));
                case "colour_data" -> cv.setValue(TuyaService.createColorBody("#" + cv.getValue()));
                case "intensity" -> {
                    HSVColor currentHSV = TuyaService.getCurrentLEDColor(id);
                    String body = TuyaService.createIntensityBody(Integer.parseInt(cv.getValue()), currentHSV);
                    cv.setValue(body);
                }
                case "switch_1" -> cv.setValue(TuyaService.createplugSwitchBody(Boolean.parseBoolean(cv.getValue())));
            }
        }
        System.out.println(gson.toJson(codeValueList));
    }
}