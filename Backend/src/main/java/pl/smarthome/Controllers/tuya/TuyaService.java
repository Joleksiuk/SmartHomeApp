package pl.smarthome.Controllers.tuya;

import com.google.gson.Gson;
import pl.smarthome.Controllers.tuya.details.DeviceDetails;
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

    public static String ledSwitch(Boolean state){
        CodeValueBoolean cv=new CodeValueBoolean("switch_led", state);
        CommandsList cl = new CommandsList(new LinkedList(List.of(cv)));
        return cl.toJson();
    }

    public static String plugSwitch(Boolean state){
        CodeValueBoolean cv=new CodeValueBoolean("switch_1", state);
        CommandsList cl = new CommandsList(new LinkedList(List.of(cv)));
        return cl.toJson();
    }

    public static String changeColor(String colorHex){
        CodeValueString cv=new CodeValueString("colour_data", HSVColor.hsvToJson( HSVColor.fromHex(colorHex)));
        CommandsList cl = new CommandsList(new LinkedList(List.of(cv)));
        return cl.toJson();
    }
    public static String changeIntensity(Integer intensity, HSVColor currentHSV){
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


}
