package pl.smarthome.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.Command;
import pl.smarthome.Models.tuya.TuyaCommand;
import pl.smarthome.Services.CommandService;
import pl.smarthome.Services.TuyaCommandService;

import java.util.List;

@RestController
@RequestMapping("commands")
public class CommandController {

    private final CommandService commandService;

    @Autowired
    public CommandController(CommandService tuyaCommandService) {
        this.commandService = tuyaCommandService;
    }

    @PostMapping
    public void createCommand(@RequestBody Command command) {
        commandService.createCommand(command);
    }

    @PutMapping
    public void updateCommand(@RequestBody Command command) {
        commandService.updateCommand(command);
    }

    @GetMapping("{id}")
    public Command findCommandById(@PathVariable Long id) {
        return commandService.findCommandById(id).orElse(null);
    }

    @GetMapping("sceneId={id}")
    public List<Command> getCommandsBySceneId(@PathVariable Long id){
        return commandService.getAllCommandsBySceneId(id);
    }

    @PostMapping("setScene/{id}")
    public void setSceneBySceneId(@PathVariable Long id) {
        commandService.setScene(id);
    }

}
