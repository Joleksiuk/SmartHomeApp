package pl.smarthome.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.smarthome.Models.Device;
import pl.smarthome.Models.House;
import pl.smarthome.Models.RolePermission;
import pl.smarthome.Models.dtos.RolePermissionDto;
import pl.smarthome.Services.DeviceService;
import pl.smarthome.Services.PermissionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("permissions")
public class PermissionController {

    private final PermissionService permissionService;
    private final DeviceService deviceService;

    @PutMapping
    public void updatePermission(@RequestBody RolePermission permission) {
        permissionService.updatePermission(permission);
    }
    @GetMapping("{deviceId}")
    public List<RolePermissionDto> getPermissionByDeviceId(@PathVariable Long deviceId){
        List<RolePermission> permissions = permissionService.getPermissionByDeviceId(deviceId);
        return permissions.stream()
                .map(per -> (
                        new RolePermissionDto(per, deviceService.findById(deviceId).get().getName()))
                ).toList();
    }

    @PostMapping()
    public List<RolePermission> getPermissionByDeviceList(@RequestBody List<Device> deviceList){
        return permissionService.getPermissionByDeviceList(deviceList);
//        List<RolePermissionDto> result = permissions.stream()
//                .map(per -> (
//                        new RolePermissionDto(per, deviceService.findById(per.getDeviceId()).get().getName()))
//                ).toList();
//        return result;
    }
}
