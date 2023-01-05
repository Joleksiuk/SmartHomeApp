package pl.smarthome.Controllers.tuya;

import com.google.gson.Gson;
import pl.smarthome.Controllers.tuya.details.DeviceDetails;
import pl.smarthome.Controllers.tuya.details.Status;
import pl.smarthome.Controllers.tuya.models.CodeValueBoolean;
import pl.smarthome.Controllers.tuya.models.CodeValueString;
import pl.smarthome.Controllers.tuya.models.CommandsList;
import pl.smarthome.Controllers.tuya.models.HSVColor;

import java.util.*;

public class TuyaService {

    public static CodeValueBoolean codeValueBoolean1 =new CodeValueBoolean("switch_led",true);
    public static CommandsList commands = new CommandsList(new LinkedList(List.of(codeValueBoolean1)));


    public static String getSampleCommands(){
        return commands.toJson();
    }

    public static String createSwitchBody(Boolean state){
        CodeValueBoolean cv=new CodeValueBoolean("switch_led", state);
        CommandsList cl = new CommandsList(new LinkedList(List.of(cv)));
        return cl.toJson();
    }

    public static String createplugSwitchBody(Boolean state){
        CodeValueBoolean cv=new CodeValueBoolean("switch_1", state);
        CommandsList cl = new CommandsList(new LinkedList(List.of(cv)));
        return cl.toJson();
    }

    public static String createColorBody(String colorHex){
        CodeValueString cv=new CodeValueString("colour_data", HSVColor.hsvToJson( HSVColor.fromHex(colorHex)));
        CommandsList cl = new CommandsList(new LinkedList(List.of(cv)));
        return cl.toJson();
    }
    public static String createIntensityBody(Integer intensity, HSVColor currentHSV){
        Gson gson=new Gson();
        currentHSV.setV(intensity);
        CodeValueString cv=new CodeValueString("colour_data", gson.toJson(currentHSV));
        CommandsList cl = new CommandsList(new LinkedList(List.of(cv)));
        return cl.toJson();
    }

    public static DeviceDetails getDeviceDetails(String id){
        String path = "/v1.0/devices/"+id;
        Object result = TuyaFunctions.execute(TuyaFunctions.getAccessToken(),path,"GET", "",new HashMap<>() );
        Gson gson=new Gson();
        return gson.fromJson(gson.toJson(result),DeviceDetails.class);
    }

    public static String multiRequest(List<CodeValueString> codeValueList, String id){

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
        Gson gson=new Gson();
        return "";
    }

    public static HSVColor getCurrentLEDColor(String id){
        DeviceDetails deviceDetails = TuyaService.getDeviceDetails(id);
        HSVColor currentHSV=new HSVColor();
        List<Status> statuses = Arrays.stream(deviceDetails.result.getStatus()).toList();
        for(Status status: statuses){
            if(Objects.equals(status.getCode(), "colour_data")){
                currentHSV = HSVColor.JsonToHSV(status.getValue().toString());
                break;
            }
        }
        return currentHSV;
    }

    public static String makeRequest(String body, String path){
        Object result2 = TuyaFunctions.execute(TuyaFunctions.getAccessToken(),path,"POST", body,new HashMap<>() );
        return result2.toString();
    }



}
