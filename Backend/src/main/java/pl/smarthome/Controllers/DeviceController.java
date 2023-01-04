package pl.smarthome.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.Device;
import pl.smarthome.Services.DeviceService;

import java.util.List;

@RestController
@RequestMapping("devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService houseService) {
        this.deviceService = houseService;
    }

    @PostMapping
    public void createDevice(@RequestBody Device house) {
        deviceService.createDevice(house);
    }

    @PutMapping
    public void updateDevice(@RequestBody Device house) {
        deviceService.updateDevice(house);
    }

    @DeleteMapping
    public void DeleteDeviceById(@RequestBody Long id) {
        deviceService.deleteDeviceById(id);
    }

    @GetMapping("{id}")
    public Device findDeviceById(@PathVariable Long id) {
        return deviceService.findById(id).orElse(null);
    }

    @GetMapping("houseId={id}")
    public List<Device> getDevicesByHouseId(@PathVariable Long id) {
        return deviceService.getDevicesByHouseId(id);
    }

}