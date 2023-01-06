package pl.smarthome.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.Instruction;
import pl.smarthome.Models.InstructionId;
import pl.smarthome.Services.InstructionService;

import java.util.List;

@RestController
@RequestMapping("instructions")
public class InstructionController {

    private final InstructionService instructionService;

    @Autowired
    public InstructionController(InstructionService instructionService) {
        this.instructionService = instructionService;
    }

    @PostMapping
    public void createInstruction(@RequestBody Instruction instruction) {
        instructionService.createInstruction(instruction);
    }

    @PutMapping
    public void updateInstruction(@RequestBody Instruction instruction) {
        instructionService.updateInstruction(instruction);
    }

    @GetMapping("sceneId={id}")
    public List<Instruction> getInstructionsBySceneId(@PathVariable Long id){
        return instructionService.getAllInstructionsBySceneId(id);
    }

    @GetMapping()
    public List<Instruction> getInstructionsBySceneId(@RequestBody InstructionId id){
        return instructionService.getAllInstructionsById(id);
    }


}
