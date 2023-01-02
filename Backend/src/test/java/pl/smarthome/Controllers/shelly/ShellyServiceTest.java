package pl.smarthome.Controllers.shelly;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShellyServiceTest {

    @Test
    void testJsonBuilder(){
        JsonObject json = new JsonObject();
        json.addProperty("key", "value");
        json.addProperty("key1", "value1");
        json.addProperty("key2", "value2");
        System.out.println(json);
    }
    @Test
    void doRequest() {
    }

    @Test
    void getRequest() {
    }

    @Test
    void postRequest() {
    }

    @Test
    void putRequest() {
    }

    @Test
    void deleteRequest() {
    }

    @Test
    void execute() {
    }
}