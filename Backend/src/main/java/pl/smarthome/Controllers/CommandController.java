package pl.smarthome.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.Command;
import pl.smarthome.Models.CommandDto;
import pl.smarthome.Services.CommandService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("commands")
public class CommandController {

    private final CommandService commandService;

    @PostMapping
    public void createInstruction(@RequestBody Command instruction) {
        commandService.createCommand(instruction);
    }

    @PutMapping
    public void updateInstruction(@RequestBody Command instruction) {
        commandService.updateCommand(instruction);
    }

    @GetMapping("sceneId={sceneId}")
    public List<Command> getInstructionsBySceneId(@PathVariable Long sceneId){
        return commandService.getAllCommandsBySceneId(sceneId);
    }
    @GetMapping("sceneId={sceneId}/deviceId={deviceId}")
    public List<CommandDto> getCommandDtosByids(@PathVariable Long sceneId, @PathVariable Long deviceId){
        return commandService.getAllCommandsDtoByIds(sceneId,deviceId);
    }


}
