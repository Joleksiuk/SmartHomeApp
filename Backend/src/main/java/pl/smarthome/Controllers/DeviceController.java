package pl.smarthome.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.Device;
import pl.smarthome.Models.dtos.DeviceDto;
import pl.smarthome.Models.dtos.UserPermisson;
import pl.smarthome.Services.DeviceService;
import pl.smarthome.Services.SceneService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("devices")
public class DeviceController {

    private final DeviceService deviceService;
    private final SceneService sceneService;

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
    public DeviceDto findDeviceById(@PathVariable Long id) {
        return sceneService.devicetoDto(deviceService.findById(id).orElse(null));
    }

    @GetMapping("houseId={id}")
    public List<DeviceDto> getDevicesByHouseId(@PathVariable Long id) {

        return deviceService.getDevicesByHouseId(id).stream().map(sceneService::devicetoDto).toList();
    }

    @PostMapping("houseId={houseId}/deviceId={deviceId}")
    public void addDeviceToHouse(@PathVariable Long houseId,@PathVariable Long deviceId) {
        deviceService.addDeviceToHouse(houseId,deviceId);
    }

}