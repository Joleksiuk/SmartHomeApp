package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.Device;
import pl.smarthome.Models.RolePermission;
import pl.smarthome.Repositories.DeviceRepository;
import pl.smarthome.Repositories.PermissionRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    private final PermissionService permissionService;
    private final PermissionRepository permissionRepository;

    public void createDevice(Device device) {
        deviceRepository.save( device);
    }

    public void updateDevice(Device device) {
        deviceRepository.save( device);
    }

    public void deleteDeviceById(Long id){
        deviceRepository.deleteById(id);
    }

    public void addDeviceToHouse(Long houseId, Long deviceId){
        Optional <Device> device =  deviceRepository.findById(deviceId);
        device.ifPresent(value -> value.setHouseId(houseId));

        List<RolePermission> roles = new LinkedList<>();
        RolePermission rolePermission1=new RolePermission(deviceId,"Guest","true","false");
        RolePermission rolePermission2=new RolePermission(deviceId,"Resident","true","true");
        RolePermission rolePermission3=new RolePermission(deviceId,"Admin","true","true");
        roles.add(rolePermission1);
        roles.add(rolePermission2);
        roles.add(rolePermission3);
        permissionRepository.saveAll(roles);
    }
    public Optional<Device> findById(Long id) {return deviceRepository.findById(id); }
    public List<Device> getDevicesByHouseId(Long houseId) {
        return deviceRepository.getAllByHouseId(houseId);
    }
}
