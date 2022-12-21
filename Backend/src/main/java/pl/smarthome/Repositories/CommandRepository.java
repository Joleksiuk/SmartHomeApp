package pl.smarthome.Repositories;

import org.springframework.data.repository.CrudRepository;
import pl.smarthome.Models.Command;

import java.util.List;
import java.util.Optional;

public interface CommandRepository  extends CrudRepository<Command, Integer>  {
    Optional<Command> findById(Integer id);
    List<Command> findAllByName(String name);
    List<Command> findAllByCategory(String category);
}