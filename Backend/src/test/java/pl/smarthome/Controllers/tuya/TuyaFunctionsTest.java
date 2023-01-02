package pl.smarthome.Controllers.tuya;

import com.google.gson.Gson;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TuyaFunctionsTest {


    @Test
    void testFunction() {
        Gson gson=new Gson();
        String getTokenPath = "/v1.0/token?grant_type=1";
        Object result = TuyaFunctions.execute(getTokenPath, "GET", "", new HashMap<>());
        TuyaResponse response = gson.fromJson(gson.toJson(result), TuyaResponse.class);
        String accessToken = response.result.access_token;
        System.out.println(accessToken);
    }
    @Test
    void getAccessToken() {
        System.out.println(TuyaFunctions.getAccessToken());
    }


    @Test
    void hsvToJson(){
        HSVColor hsv = HSVColor.fromHex("#ff0a37");
        Gson gson = new Gson();
        String json = gson.toJson(hsv);
        System.out.println(json);
    }
}