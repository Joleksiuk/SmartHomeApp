package pl.smarthome.Controllers.users;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.users.ShellyUser;
import pl.smarthome.Repositories.ShellyUserRepository;
import pl.smarthome.Services.ShellyUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("shelly_users")
public class ShellyUserController {

    private final ShellyUserRepository shellyUserRepository;
    private final ShellyUserService shellyUserService ;

    @DeleteMapping("{id}")
    public void deleteShellyUserById(@PathVariable Long id){
        shellyUserRepository.deleteById(id);
    }

    @PostMapping
    public ShellyUser createShellyUser(@RequestBody ShellyUser shellyUser) {
        return shellyUserService.createShellyUser(shellyUser);
    }
    @PutMapping
    public void updateShellyUser(@RequestBody ShellyUser shellyUser) {
        shellyUserService.updateShellyUser(shellyUser);
    }

    @GetMapping("{id}")
    public ShellyUser findShellyUserById(@PathVariable Long id) {
        return shellyUserService.findShellyUserById(id).orElse(null);
    }

}
