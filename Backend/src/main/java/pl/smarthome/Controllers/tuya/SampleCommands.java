package pl.smarthome.Controllers.tuya;

import java.util.LinkedList;
import java.util.List;

public class SampleCommands {

    public static CodeValue codeValue1=new CodeValue("switch_led",true);
    public static CommandsList commands = new CommandsList(new LinkedList(List.of(codeValue1)));

    public static String getSampleCommands(){
        return commands.toJson();
    }
}
