package pl.smarthome.Controllers.shelly;

import com.google.gson.JsonObject;
import org.apache.xpath.operations.Bool;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
    void execute() throws JSONException {
        //String jsonString = "{A=1,\"B\"={\"a\"=1,\"b\"=1}}";
        String jsonString = "[{ison=true, has_timer=false, timer_started=0, timer_duration=0, timer_remaining=0, brightness=44, white=55, temp=4790}]";
        JSONArray array =new JSONArray(jsonString);

        JSONObject light = new JSONObject( array.getString(0));
        System.out.println("ison: "+light.getBoolean("ison"));
        System.out.println("temp: "+light.getInt("temp"));
        System.out.println("brightness: "+light.getInt("brightness"));
        System.out.println("white: "+light.getInt("white"));

        String xd ="[{ison=true, has_timer=false, timer_started=0, timer_duration=0, timer_remaining=0, brightness=44, white=55, temp=4790}]";

        System.out.println(xd.replaceAll("([{,])\\s*([a-zA-Z]+)\\s*:", "$1\"$2\":"));

        String a = xd.replaceAll("=","\":");
        String b = a.replaceAll(", ", ", \"");
        String c = b.replaceAll("ison", "\"ison");
        System.out.println(c);
    }

    @Test
    void testBoooleanParse(){
        System.out.println(Boolean.getBoolean("True"));
        System.out.println(Boolean.getBoolean("true"));
        System.out.println(Boolean.getBoolean("TRUE"));

    }
}