package pl.smarthome.Controllers;

import org.junit.jupiter.api.Test;
import pl.smarthome.Controllers.tuya.CodeValue;
import pl.smarthome.Controllers.tuya.CommandsList;

class CommandsTest {

    CommandsList commandsList =new CommandsList();
    @Test
    void addCommand() {
        commandsList.addCommand( new CodeValue("Switch_led",true));
        System.out.println(commandsList.getCommands().get(0).toString());
    }

    @Test
    void toJson(){
        commandsList.addCommand(new CodeValue("Switch_led",true));
        commandsList.addCommand(new CodeValue("sds",false));
        System.out.println(commandsList.toJson());
    }


}