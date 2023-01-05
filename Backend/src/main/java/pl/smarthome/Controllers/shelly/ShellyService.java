package pl.smarthome.Controllers.shelly;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.smarthome.Controllers.tuya.details.CodeValue;
import pl.smarthome.Controllers.tuya.models.CodeValueString;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShellyService {

    private final RestTemplate restTemplate;

    private final String authKey = "ZWQxZTN1aWQ8C2F82EE9C857CE6FCAE87A3D4103699E42021BC811BA77FDC325A1F52AA8E4EA828527A61F8E75E";
    private final String baseUrl = "https://shelly-38-eu.shelly.cloud";

    public String makeShellyRequest(MultiValueMap<String, String> map, String path){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        map.add("auth_key",authKey);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map,headers);
        ResponseEntity<Object> response = restTemplate.exchange(baseUrl+path, HttpMethod.POST, entity, Object.class);
        return response.getBody().toString();
    }

    public String executeShellyCommand(String command, String value, String deviceId, String path){

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id",deviceId);
        map.add(command,value);
        return makeShellyRequest(map,path);
    }

    public String multiControl(List<CodeValue> codeValueList, String deviceId, String path){
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id",deviceId);
        codeValueList.forEach(cv -> map.add(cv.getCode(), cv.getValue().toString()));
        return makeShellyRequest(map,path);
    }

}
