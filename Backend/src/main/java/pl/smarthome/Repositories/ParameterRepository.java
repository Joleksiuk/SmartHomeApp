package pl.smarthome.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smarthome.Models.tuya.Parameter;

import java.util.Optional;

public interface ParameterRepository  extends JpaRepository<Parameter, Integer> {
    Optional<Parameter> findById(Integer id);
    Optional<Parameter> findByName(String name);

}