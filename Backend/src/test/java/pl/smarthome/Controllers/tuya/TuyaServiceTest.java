package pl.smarthome.Controllers.tuya;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.*;
import org.junit.jupiter.api.Test;
import pl.smarthome.AES.AES;
import pl.smarthome.Controllers.tuya.details.CodeValue;
import pl.smarthome.Controllers.tuya.details.CommandList;
import pl.smarthome.Models.users.TuyaUser;

import java.util.*;

class TuyaServiceTest {

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
    void getTestAESUtils(){

        TuyaUser user = new TuyaUser(Long.parseLong("10"),
                "https://openapi.tuyaeu.com",
                "7u5g78ek3yp4v7pfd735",
                "8e7be48e8b4146089929474d30d0488f");
        user.setServer(AES.encrypt(user.getServer()));
        user.setAccessId(AES.encrypt(user.getAccessId()));
        user.setSecretKey(AES.encrypt(user.getSecretKey()));

        System.out.println(AES.decrypt(user.getServer()));
        System.out.println(AES.decrypt(user.getAccessId()));
        System.out.println(AES.decrypt(user.getSecretKey()));
    }

    @Test
    void testMulti(){
        CommandList commandList=new CommandList();
        commandList.getCommands().add(new CodeValue("code1", "value1"));
        Gson gson=new Gson();
        System.out.println(gson.toJson(commandList));
    }

}