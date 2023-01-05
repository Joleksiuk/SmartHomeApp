package pl.smarthome.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.Instruction;
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

    @GetMapping("{id}")
    public Instruction findInstructionById(@PathVariable Long id) {
        return instructionService.findInstructionbyId(id).orElse(null);
    }

    @GetMapping("sceneId={id}")
    public List<Instruction> getCommandsBySceneId(@PathVariable Long id){
        return instructionService.getAllInstructionsBySceneId(id);
    }

    @PostMapping("executeScene/{id}")
    public void executeSceneById(@PathVariable Long id) {
        instructionService.executeScene(id);
    }

}
