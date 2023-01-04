package pl.smarthome.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smarthome.Models.House;

import java.util.List;
import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, Long> {
    List<House> getAllByOwnerId(Long ownerId);
    Optional<House> findById(Long houseId);
    void deleteById(Long id);
}
