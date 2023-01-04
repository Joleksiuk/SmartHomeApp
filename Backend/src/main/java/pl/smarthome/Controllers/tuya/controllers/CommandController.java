package pl.smarthome.Controllers.tuya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.tuya.TuyaCommand;
import pl.smarthome.Services.CommandService;

import java.util.List;

@RestController
@RequestMapping("commands")
public class CommandController {

    private final CommandService commandService;

    @Autowired
    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    public void createCommand(@RequestBody TuyaCommand tuyaCommand) {
        commandService.createCommand(tuyaCommand);
    }

    @PutMapping
    public void updateCommand(@RequestBody TuyaCommand tuyaCommand) {
        commandService.updateCommand(tuyaCommand);
    }

    @GetMapping("{id}")
    public TuyaCommand findCommandById(@PathVariable Integer id) {
        return commandService.findCommandById(id).orElse(null);
    }

    @GetMapping
    public List<TuyaCommand> getCommands(){
        return commandService.getAllCommands();
    }
}
