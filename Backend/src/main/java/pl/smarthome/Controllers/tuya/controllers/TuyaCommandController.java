package pl.smarthome.Controllers.tuya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.tuya.TuyaCommand;
import pl.smarthome.Services.TuyaCommandService;

import java.util.List;

@RestController
@RequestMapping("tuya_commands")
public class TuyaCommandController {

    private final TuyaCommandService tuyaCommandService;

    @Autowired
    public TuyaCommandController(TuyaCommandService tuyaCommandService) {
        this.tuyaCommandService = tuyaCommandService;
    }

    @PostMapping
    public void createCommand(@RequestBody TuyaCommand tuyaCommand) {
        tuyaCommandService.createCommand(tuyaCommand);
    }

    @PutMapping
    public void updateCommand(@RequestBody TuyaCommand tuyaCommand) {
        tuyaCommandService.updateCommand(tuyaCommand);
    }

    @GetMapping("{id}")
    public TuyaCommand findCommandById(@PathVariable Integer id) {
        return tuyaCommandService.findCommandById(id).orElse(null);
    }

    @GetMapping
    public List<TuyaCommand> getCommands(){
        return tuyaCommandService.getAllCommands();
    }
}
