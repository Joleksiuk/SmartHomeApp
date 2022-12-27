package pl.smarthome.Controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.users.TuyaUser;
import pl.smarthome.Services.TuyaUserService;

@RestController
@RequestMapping("tuya_users")
public class TuyaUserController {

    private final TuyaUserService tuyaUserService;

    @Autowired
    public TuyaUserController(TuyaUserService tuyaUserService) {
        this.tuyaUserService = tuyaUserService;
    }

    @PostMapping
    public void createTuyaUser(@RequestBody TuyaUser tuyaUser) {
        tuyaUserService.createTuyaUser(tuyaUser);
    }

    @PutMapping
    public void updateTuyaUser(@RequestBody TuyaUser tuyaUser) {
        tuyaUserService.updateTuyaUser(tuyaUser);
    }

    @GetMapping("{id}")
    public TuyaUser findTuyaUserById(@PathVariable Integer id) {
        return tuyaUserService.findTuyaUserById(id).orElse(null);
    }

}