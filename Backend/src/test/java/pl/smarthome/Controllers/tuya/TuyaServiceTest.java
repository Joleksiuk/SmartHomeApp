package pl.smarthome.Controllers.tuya;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.*;
import org.junit.jupiter.api.Test;
import pl.smarthome.Controllers.tuya.details.DeviceDetails;
import pl.smarthome.Controllers.tuya.models.CodeValueString;
import pl.smarthome.Controllers.tuya.details.CodeValue;
import pl.smarthome.Controllers.tuya.details.CommandList;

import java.util.*;

class TuyaServiceTest {

    @Test
    void getDeviceDetails() {
        String path = "/v1.0/devices/bf2b8148e20535ca2eaik5";
        Object result = TuyaFunctions.execute(TuyaFunctions.getAccessToken(),path,"GET", "",new HashMap<>() );
        Gson gson=new Gson();
        System.out.println(gson.fromJson(gson.toJson(result), DeviceDetails.class));
    }

    @Test
    void testMultiRequestBody(){

        class MyList {

            public List<CodeValueString> commands=new LinkedList<>();
        }

        String id="1";
        List<CodeValueString> codeValueList=new LinkedList<>();

        codeValueList.add(new CodeValueString("switch_led","true"));
        codeValueList.add(new CodeValueString("switch_led","false"));
        Gson gson=new Gson();


        MyList myList=new MyList();
        myList.commands= codeValueList;
        String a = gson.toJson(myList);
        System.out.println(a);
    }

    @Test
    void testInterfacesCodeValueToJson(){

        interface CV{
            String toJson(Gson gson);
        }

        @AllArgsConstructor
        class A implements CV{
            String Code;
            String Value;
            @Override
            public String toJson(Gson gson) {
                return gson.toJson(this);
            }
        }
        @AllArgsConstructor
        class B implements CV{
            String Code;
            Boolean Value;
            @Override
            public String toJson(Gson gson) {
                return gson.toJson(this);
            }
        }

        class MyList{
            public List<CV> commands= new LinkedList<>();
        }

        Gson gson=new Gson();
        MyList myList=new MyList();
        myList.commands.add(new A("A","A"));
        myList.commands.add(new B("B",true));

        try{
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(myList);
            System.out.println(json);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    void testMulti(){
        CommandList commandList=new CommandList();
        commandList.getCommands().add(new CodeValue("code1", "value1"));
        commandList.getCommands().add(new CodeValue("switch_led", true));
        Gson gson=new Gson();
        System.out.println(gson.toJson(commandList));
    }

}