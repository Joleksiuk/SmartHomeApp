package pl.smarthome.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.House;
import pl.smarthome.Models.HouseUser;
import pl.smarthome.Models.dtos.HouseUserDto;
import pl.smarthome.Models.ids.HouseUserId;
import pl.smarthome.Services.HouseUserService;


import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("house_users")
public class HouseUserController {

    private final HouseUserService houseUserService;

    @Autowired
    public HouseUserController(HouseUserService service) {
        this.houseUserService = service;
    }

    @PostMapping
    public void createHouseUser(@RequestBody HouseUser houseUser) {
        houseUserService.createHouseUser(houseUser);
    }

    @PutMapping
    public void updateHouseUser(@RequestBody HouseUser houseUser) {
        houseUserService.updateHouseUser(houseUser);
    }

    @DeleteMapping
    public void DeleteHouseUserById(@PathVariable Long userId, @PathVariable Long houseId) {
        HouseUserId id=new HouseUserId(userId,houseId);
        houseUserService.deleteHouseUserById(id);
    }

    @GetMapping("userId={userId}/houseId={houseId}")
    public HouseUserDto findHouseUserById(@PathVariable Long userId, @PathVariable Long houseId) {
        List<HouseUser> houseUsers = houseUserService.getAllHouseUsersByHouseId(houseId);
        List<HouseUserDto> dtos= houseUserService.convertHouseUserToDto(houseUsers);
        return dtos.stream().filter(dto -> (Objects.equals(dto.getUserId(), userId))).findFirst().orElse(null);

    }

    @GetMapping("houseId={id}")
    public List<HouseUserDto> getAllHouseUsersByHouseId(@PathVariable Long id) {
        List<HouseUser> houseUsers = houseUserService.getAllHouseUsersByHouseId(id);
        return houseUserService.convertHouseUserToDto(houseUsers);
    }

    @PostMapping("houseId={hid}/username={name}")
    public String addUserToHouse(@PathVariable Long hid, @PathVariable String name){
        return houseUserService.addHouseUser(hid,name);
    }

    @GetMapping("userId={id}")
    public List<House> getAllHousesOfUser(@PathVariable Long id) {
        return houseUserService.getHousesByUserId(id);
    }

}
