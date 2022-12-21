package pl.smarthome.Repositories;

import org.springframework.data.repository.CrudRepository;
import pl.smarthome.Models.Parameter;

import java.util.Optional;

public interface ParameterRepository  extends CrudRepository<Parameter, Integer>  {
    Optional<Parameter> findById(Integer id);
    Optional<Parameter> findByName(String name);

}