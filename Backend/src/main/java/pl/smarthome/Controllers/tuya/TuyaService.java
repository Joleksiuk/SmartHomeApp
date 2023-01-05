package pl.smarthome.Controllers.tuya;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import pl.smarthome.Controllers.tuya.details.DeviceDetails;
import pl.smarthome.Controllers.tuya.details.Status;
import pl.smarthome.Controllers.tuya.models.HSVColor;
import pl.smarthome.Controllers.tuya.details.CodeValue;
import pl.smarthome.Controllers.tuya.details.CommandList;

import java.util.*;

@Service
public class TuyaService {

    public DeviceDetails getDeviceDetails(String id){
        String path = "/v1.0/devices/"+id;
        Object result = TuyaFunctions.execute(TuyaFunctions.getAccessToken(),path,"GET", "",new HashMap<>() );
        Gson gson=new Gson();
        return gson.fromJson(gson.toJson(result),DeviceDetails.class);
    }

    public String multiCommandsRequest(List<CodeValue> codeValueList, String id){

        for(CodeValue cv: codeValueList){
            switch (cv.getCode()) {
                case "colour_data" -> cv.setValue(HSVColor.hsvToJson( HSVColor.fromHex(cv.getValue().toString())));
                case "intensity" -> {
                    HSVColor currentHSV = getCurrentLEDColor(id);
                    currentHSV.setV(Integer.parseInt( cv.getValue().toString()));
                    cv.setValue(currentHSV.toJson());
                    cv.setCode("colour_data");
                }
            }
        }
        CommandList commandList=new CommandList(codeValueList);
        return makeSingleRequest(commandList.toJson(),"/v1.0/devices/"+id+"/commands","POST");
    }

    public HSVColor getCurrentLEDColor(String id){
        DeviceDetails deviceDetails = getDeviceDetails(id);
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

    public String makeSingleRequest(String body, String path, String method){
        Object result2 = TuyaFunctions.execute(TuyaFunctions.getAccessToken(),path,method, body,new HashMap<>() );
        return result2.toString();
    }



}
