package pl.smarthome.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.Command;
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
    public void createCommand(@RequestBody Command command) {
        commandService.createCommand(command);
    }

    @PutMapping
    public void updateCommand(@RequestBody Command command) {
        commandService.updateCommand(command);
    }

    @GetMapping("{id}")
    public Command findCommandById(@PathVariable Integer id) {
        return commandService.findCommandById(id).orElse(null);
    }

    @GetMapping
    public List<Command> getCommands(){
        return commandService.getAllCommands();
    }
}
