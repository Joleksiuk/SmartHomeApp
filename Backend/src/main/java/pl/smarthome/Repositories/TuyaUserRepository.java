package pl.smarthome.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.smarthome.Models.users.TuyaUser;

import java.util.Optional;

@Repository
public interface TuyaUserRepository extends JpaRepository<TuyaUser, Long> {
    Optional<TuyaUser> findById(Integer id);
}
