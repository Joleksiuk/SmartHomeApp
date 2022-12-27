package pl.smarthome.Controllers.tuya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.Parameter;
import pl.smarthome.Services.ParameterService;

import java.util.List;

@RestController
@RequestMapping("parameters")
public class ParameterController {


    private final ParameterService parameterService;

    @Autowired
    public ParameterController(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @PostMapping
    public void createParameter(@RequestBody Parameter parameter) {
        parameterService.createParameter(parameter);
    }

    @PutMapping
    public void updateParameter(@RequestBody Parameter parameter) {
        parameterService.updateParameter(parameter);
    }

    @GetMapping("{id}")
    public Parameter findParameterById(@PathVariable Integer id) {
        return parameterService.findParameterById(id).orElse(null);
    }

    @GetMapping
    public List<Parameter> getParameters(){
        return parameterService.getAllParameters();
    }
}
