package pl.smarthome.Repositories;

import org.springframework.data.repository.CrudRepository;
import pl.smarthome.Models.tuya.TuyaCommand;

import java.util.List;
import java.util.Optional;

public interface CommandRepository  extends CrudRepository<TuyaCommand, Integer>  {
    Optional<TuyaCommand> findById(Integer id);
    List<TuyaCommand> findAllByName(String name);
    List<TuyaCommand> findAllByCategory(String category);
}