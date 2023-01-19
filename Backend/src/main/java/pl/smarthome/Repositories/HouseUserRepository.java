package pl.smarthome.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smarthome.Models.HouseUser;
import pl.smarthome.Models.ids.HouseUserId;

import java.util.List;
import java.util.Optional;

public interface HouseUserRepository extends JpaRepository<HouseUser, HouseUserId> {

    Optional<HouseUser> findById(HouseUserId houseUserId);
    List<HouseUser> getAllByHouseId(Long houseId);
}
