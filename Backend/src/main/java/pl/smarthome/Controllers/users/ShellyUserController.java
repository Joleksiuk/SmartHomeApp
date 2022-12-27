package pl.smarthome.Controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.users.ShellyUser;
import pl.smarthome.Services.ShellyUserService;

@RestController
@RequestMapping("shelly_users")
public class ShellyUserController {

    private final ShellyUserService shellyUserService ;

    @Autowired
    public ShellyUserController(ShellyUserService shellyUserService) {
        this.shellyUserService = shellyUserService;
    }

    @PostMapping
    public void createShellyUser(@RequestBody ShellyUser shellyUser) {
        shellyUserService.createShellyUser(shellyUser);
    }

    @PutMapping
    public void updateShellyUser(@RequestBody ShellyUser shellyUser) {
        shellyUserService.updateShellyUser(shellyUser);
    }

    @GetMapping("{id}")
    public ShellyUser findShellyUserById(@PathVariable Integer id) {
        return shellyUserService.findShellyUserById(id).orElse(null);
    }

}
