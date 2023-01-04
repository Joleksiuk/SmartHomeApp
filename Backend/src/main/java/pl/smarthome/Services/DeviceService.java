package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.Device;
import pl.smarthome.Repositories.DeviceRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public void createDevice(Device device) {
        deviceRepository.save( device);
    }

    public void updateDevice(Device device) {
        deviceRepository.save( device);
    }

    public void deleteDeviceById(Long id){
        deviceRepository.deleteById(id);
    }

    public Optional<Device> findById(Long id) {return deviceRepository.findById(id); }
    public List<Device> getDevicesByHouseId(Long houseId) {
        return deviceRepository.getAllByHouseId(houseId);
    }
}
