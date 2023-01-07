package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.Device;
import pl.smarthome.Models.RolePermission;
import pl.smarthome.Repositories.DeviceRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    private final PermissionService permissionService;

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

        permissionService.createPermission(new RolePermission(deviceId,"Guest","true","false"));
        permissionService.createPermission(new RolePermission(deviceId,"Resident","true","true"));
    }
    public Optional<Device> findById(Long id) {return deviceRepository.findById(id); }
    public List<Device> getDevicesByHouseId(Long houseId) {
        return deviceRepository.getAllByHouseId(houseId);
    }
}
