package pl.smarthome.Controllers.users;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.users.TuyaUser;
import pl.smarthome.Repositories.TuyaUserRepository;
import pl.smarthome.Services.TuyaUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("tuya_users")
public class TuyaUserController {

    private final TuyaUserService tuyaUserService;
    private final TuyaUserRepository tuyaUserRepository;

    @PostMapping
    public TuyaUser createTuyaUser(@RequestBody TuyaUser tuyaUser) {
        return tuyaUserService.createTuyaUser(tuyaUser);
    }

    @DeleteMapping("{id}")
    public void deleteTuyaUserById(@PathVariable Long id){
        tuyaUserRepository.deleteById(id);
    }

    @PutMapping
    public void updateTuyaUser(@RequestBody TuyaUser tuyaUser) {
        tuyaUserService.updateTuyaUser(tuyaUser);
    }

    @GetMapping("{id}")
    public TuyaUser findTuyaUserById(@PathVariable Long id) {
        return tuyaUserService.findTuyaUserById(id).orElse(null);
    }

}