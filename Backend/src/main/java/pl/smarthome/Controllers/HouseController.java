package pl.smarthome.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.House;
import pl.smarthome.Models.dtos.HouseDto;
import pl.smarthome.Services.HouseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("houses")
public class HouseController {

    private final HouseService houseService;

    @PostMapping("{userId}")
    public void createComponent(@RequestBody House house ,@PathVariable Long userId) {
        houseService.createHouse(house,userId);
    }

    @PutMapping
    public void updateComponent(@RequestBody House house) {
        houseService.updateHouse(house);
    }

    @DeleteMapping("{houseId}")
    public void DeleteHouseById(@PathVariable Long houseId) {
        houseService.deleteHouseById(houseId);
    }

    @GetMapping("houseId={id}")
    public House findHouseById(@PathVariable Long id) {
        return houseService.findHouseByHouseId(id).orElse(null);
    }

    @GetMapping("ownerId={id}")
    public List<House> findHouseByOwnerId(@PathVariable Long id) {
        return houseService.findHousesByOwnerId(id);
    }

    @GetMapping("{houseId}/data")
    public HouseDto getHouseData(@PathVariable Long houseId){
        return houseService.getHouseData(houseId);
    }

    @GetMapping("{houseId}/{userId}/data")
    public HouseDto getHouseDataOfUser(@PathVariable Long houseId, @PathVariable Long userId){
        return houseService.getHouseDataOfUser(houseId,userId);
    }

    @DeleteMapping("{houseId}/{deviceId}")
    public void deleteDeviceFromHouse(@PathVariable Long houseId, @PathVariable Long deviceId){
        houseService.deleteDeviceFromHouse(deviceId,houseId);
    }
}
