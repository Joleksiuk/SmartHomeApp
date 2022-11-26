package pl.smarthome.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.Component;
import pl.smarthome.Services.ComponentService;

import java.util.List;

@RestController
@RequestMapping("component")
public class ComponentController {


    private final ComponentService componentService;

    @Autowired
    public ComponentController(ComponentService componentService) {
        this.componentService= componentService;
    }

    @PostMapping
    public void createComponent(@RequestBody Component component) {
        componentService.createComponent(component);
    }

    @PutMapping
    public void updateComponent(@RequestBody Component component) {
        componentService.updateComponent(component);
    }

    @GetMapping("{id}")
    public Component findComponentById(@PathVariable Integer id) {
        return componentService.findComponentById(id).orElse(null);
    }

    @GetMapping
    public List<Component> getComponents(){
        return componentService.getAllComponents();
    }
}
