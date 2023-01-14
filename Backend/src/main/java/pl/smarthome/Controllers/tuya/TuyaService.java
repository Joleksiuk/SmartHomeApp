package pl.smarthome.Controllers.tuya;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.bcel.classfile.Code;
import org.springframework.stereotype.Service;
import pl.smarthome.Controllers.tuya.details.*;
import pl.smarthome.Controllers.tuya.details.HSVColor;
import pl.smarthome.Models.Device;
import pl.smarthome.Models.users.TuyaUser;
import pl.smarthome.Repositories.DeviceRepository;
import pl.smarthome.Repositories.TuyaUserRepository;

import java.util.*;

@RequiredArgsConstructor
@Service
public class TuyaService {

    private final TuyaUserRepository tuyaUserRepository;
    private final DeviceRepository deviceRepository;

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

    public List<CodeValue> getDeviceStatus(Long id, Long userId){
        Device device = deviceRepository.findById(id).orElse(null);
        TuyaUser user = tuyaUserRepository.findById(userId).orElse(null);
        if(user!=null) {
            String path = "/v1.0/iot-03/devices/"+device.getSpecificId()+"/status";
            Object result = TuyaFunctions.execute(TuyaFunctions.getAccessToken(user), path, "GET", "", new HashMap<>(), user);
            Gson gson = new Gson();
            List<CodeValue> cvs = gson.fromJson(gson.toJson(result), TuyaStatusResponse.class).getResult();
            CodeValue el=new CodeValue("test",0);
            for(CodeValue cv:cvs){
                if(cv.getCode().equals("colour_data")){
                    HSVColor hsv = gson.fromJson(cv.getValue().toString(),HSVColor.class);
                    cv.setValue(HSVColor.toHex(hsv));
                    el =new CodeValue("Intensity",hsv.getV());
                }
            }
            cvs.add(el);
            return cvs;
        }
        return null;
    }

    public String getPowerConsumption(String id, Long userId){
        TuyaUser user = tuyaUserRepository.findById(userId).orElse(null);
        if(user!=null) {
            String base = "/v1.0/iot-03/energy/electricity/device/nodes/statistics-sum";
            String path = base+"?energy_action=consume&statisticsType=day&startTime=20230101&endTime=20230108&containChilds=false&device_ids="+id;
            Object result = TuyaFunctions.execute(TuyaFunctions.getAccessToken(user), path, "GET", "", new HashMap<>(), user);
            Gson gson = new Gson();
            return gson.toJson(result);
        }
        return null;
    }

    public String multiCommandsRequest(List<CodeValue> codeValueList, String id, Long userId){

        for(CodeValue cv: codeValueList){
            switch (cv.getCode()) {
                case "switch_led", "switch_1" -> cv.setValue(Boolean.valueOf(cv.getValue().toString()));
                case "colour_data" -> {
                    String hex = cv.getValue().toString();
                    if(hex.charAt(0)!='#'){
                        hex='#'+hex;
                    }
                    HSVColor color = HSVColor.fromHex(hex);
                    String hsv = HSVColor.hsvToJson(color);
                    cv.setValue(hsv);
                }
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
