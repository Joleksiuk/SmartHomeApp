package pl.smarthome.Controllers.shelly;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

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
        return response.toString();
    }

    public void executeShellyCommand(String command, String value, String deviceId, String path){

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id",deviceId);
        map.add(command,value);
        makeShellyRequest(map,path);
    }
}
