package pl.smarthome.Controllers.tuya;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Controllers.tuya.details.DeviceDetails;
import pl.smarthome.Controllers.tuya.details.Status;
import pl.smarthome.Controllers.tuya.models.HSVColor;
import pl.smarthome.Controllers.tuya.details.CodeValue;
import pl.smarthome.Controllers.tuya.details.CommandList;
import pl.smarthome.Models.users.TuyaUser;
import pl.smarthome.Repositories.TuyaUserRepository;

import java.util.*;

@RequiredArgsConstructor
@Service
public class TuyaService {

    private final TuyaUserRepository tuyaUserRepository;

    public DeviceDetails getDeviceDetails(String id, Long userId){
        TuyaUser user = tuyaUserRepository.findById(userId).orElse(null);
        if(user!=null) {
            String path = "/v1.0/devices/" + id;
            Object result = TuyaFunctions.execute(TuyaFunctions.getAccessToken(user), path, "GET", "", new HashMap<>(), user);
            Gson gson = new Gson();
            return gson.fromJson(gson.toJson(result), DeviceDetails.class);
        }
        return null;
    }

    public String multiCommandsRequest(List<CodeValue> codeValueList, String id, Long userId){

        for(CodeValue cv: codeValueList){
            switch (cv.getCode()) {
                case "switch_led", "switch_1" -> cv.setValue(Boolean.valueOf(cv.getValue().toString()));
                case "colour_data" -> cv.setValue(HSVColor.hsvToJson( HSVColor.fromHex(cv.getValue().toString())));
                case "intensity" -> {
                    HSVColor currentHSV = getCurrentLEDColor(id,userId);
                    currentHSV.setV(Integer.parseInt( cv.getValue().toString()));
                    cv.setValue(currentHSV.toJson());
                    cv.setCode("colour_data");
                }
            }
        }
        CommandList commandList=new CommandList(codeValueList);
        return makeSingleRequest(commandList.toJson(),"/v1.0/devices/"+id+"/commands","POST", userId);
    }

    public HSVColor getCurrentLEDColor(String id,Long userId){
        DeviceDetails deviceDetails = getDeviceDetails(id,userId);
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

    public String makeSingleRequest(String body, String path, String method, Long userId){

        TuyaUser user = tuyaUserRepository.findById(userId).orElse(null);
        if(user!=null){
            Object result2 = TuyaFunctions.execute(TuyaFunctions.getAccessToken(user),path,method, body,new HashMap<>(), user );
            return result2.toString();
        }
        return "User id is not correct";
    }

}
