package pl.smarthome.Repositories;

import org.springframework.data.repository.CrudRepository;
import pl.smarthome.Models.Component;

import java.util.Optional;

public interface ComponentRepository  extends CrudRepository<Component, Integer>  {
    Optional<Component> findById(Integer id);
    Optional<Component> findByName(String name);
}
