package pl.smarthome.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smarthome.Models.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findById(Long id);
    List<Device> getAllByHouseId(Long houseId);
    void deleteAllByHouseId(Long houseId);
}
