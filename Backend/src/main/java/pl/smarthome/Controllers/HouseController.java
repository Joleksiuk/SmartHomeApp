package pl.smarthome.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.House;
import pl.smarthome.Services.HouseService;

import java.util.List;

@RestController
@RequestMapping("houses")
public class HouseController {

    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping
    public void createComponent(@RequestBody House house) {
        houseService.createHouse(house);
    }

    @PutMapping
    public void updateComponent(@RequestBody House house) {
        houseService.updateHouse(house);
    }

    @DeleteMapping
    public void DeleteHouseById(@RequestBody Long id) {
        houseService.deleteHouseById(id);
    }

    @GetMapping("houseId={id}")
    public House findHouseById(@PathVariable Long id) {
        return houseService.findHouseByHouseId(id).orElse(null);
    }

    @GetMapping("ownerId={id}")
    public List<House> findHouseByOwnerId(@PathVariable Long id) {
        return houseService.findHousesByOwnerId(id);
    }

}
