package pl.smarthome.Controllers.shelly;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.smarthome.AES.AES;
import pl.smarthome.Controllers.tuya.details.CodeValue;
import pl.smarthome.Models.users.ShellyUser;
import pl.smarthome.Repositories.ShellyUserRepository;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShellyService {

    private final RestTemplate restTemplate;
    private final ShellyUserRepository shellyUserRepository;

    public String makeShellyRequest(MultiValueMap<String, String> map, String path, Long userid){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        ShellyUser user = shellyUserRepository.findById(userid).orElse(null) ;
        String autKey =AES.decrypt(user.getAuth_key());
        String baseUrl= AES.decrypt(user.getServer());
        map.add("auth_key",  autKey);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map,headers);
        ResponseEntity<Object> response = restTemplate.exchange(baseUrl+path, HttpMethod.POST, entity, Object.class);
        return response.getBody().toString();
    }

    public String executeShellyCommand(String command, String value, String deviceId, String path,Long userid ){

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id",deviceId);
        map.add(command,value);
        return makeShellyRequest(map,path, userid);
    }

    public String multiControl(List<CodeValue> codeValueList, String deviceId, String path, Long userid){
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id",deviceId);
        codeValueList.forEach(cv -> map.add(cv.getCode(), cv.getValue().toString()));
        return makeShellyRequest(map,path, userid);
    }

    public List<CodeValue> getStatus(String deviceId, Long userId){
        List<CodeValue> cvs =new LinkedList<>();

        String response = executeShellyCommand("","",deviceId,"/device/status",userId);

        String [] a = response.split("lights=");
        String [] b = a[1].split(", has_update");

        String e = b[0].replaceAll("=","\":");
        String f = e.replaceAll(", ", ", \"");
        String g = f.replaceAll("ison", "\"ison");
        String h = g.substring(1,g.length()-1);
        JSONObject light = new JSONObject(h);

        cvs.add(new CodeValue("temp", light.getInt("temp")));
        cvs.add(new CodeValue("brightness", light.getInt("brightness")));
        cvs.add(new CodeValue("white", light.getInt("white")));
        if(light.getBoolean("ison")){
            cvs.add(new CodeValue("switch", "on"));
        }
        else {
            cvs.add(new CodeValue("switch", "off"));
        }
        return cvs;
    }
    private static final String root = "https://shelly-38-eu.shelly.cloud";
    public Boolean areShellyCredentialsValid(String path,String authKey){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("auth_key",authKey);
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map,headers);
            restTemplate.exchange(path, HttpMethod.POST, entity, Object.class);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean verifyDeviceId(Long userId, String deviceId){
        try{
            executeShellyCommand("","",deviceId,"/device/status",userId);
            return true;

        }catch (Exception e){
            return false;
        }
    }


}
