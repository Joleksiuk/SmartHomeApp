package pl.smarthome.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.smarthome.Models.users.ShellyUser;

import java.util.Optional;

@Repository
public interface ShellyUserRepository extends JpaRepository<ShellyUser, Long> {
    Optional<ShellyUser> findById(Long id);
    void deleteById(Long id);
}
