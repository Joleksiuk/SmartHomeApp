package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.Device;
import pl.smarthome.Models.RolePermission;
import pl.smarthome.Models.Scene;
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
        permissionRepository.deleteAll(permissionRepository.getAllByDeviceId(id).stream().toList());
        deviceRepository.deleteById(id);
    }

    public void addDeviceToHouse(Device device){
        List<RolePermission> roles = new LinkedList<>();
        RolePermission rolePermission1=new RolePermission(device.getId(),"Guest","true","false");
        RolePermission rolePermission2=new RolePermission(device.getId(),"Resident","true","true");
        RolePermission rolePermission3=new RolePermission(device.getId(),"Admin","true","true");
        roles.add(rolePermission1);
        roles.add(rolePermission2);
        roles.add(rolePermission3);
        permissionRepository.saveAll(roles);
    }
    public Optional<Device> findById(Long id) {return deviceRepository.findById(id); }
    public List<Device> getDevicesByHouseId(Long houseId) {
        return deviceRepository.getAllByHouseId(houseId);
    }

    public void deleteAllDevicesByHouseId(Long houseId){
        deviceRepository.getAllByHouseId(houseId).forEach(device -> deleteDeviceById(device.getId()));
    }

}
