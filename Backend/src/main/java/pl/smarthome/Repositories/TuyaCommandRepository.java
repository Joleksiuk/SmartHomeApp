package pl.smarthome.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smarthome.Models.tuya.TuyaCommand;

import java.util.List;
import java.util.Optional;

public interface TuyaCommandRepository extends JpaRepository<TuyaCommand, Long> {
    Optional<TuyaCommand> findById(Integer id);
    List<TuyaCommand> findAllByName(String name);
    List<TuyaCommand> findAllByCategory(String category);
}