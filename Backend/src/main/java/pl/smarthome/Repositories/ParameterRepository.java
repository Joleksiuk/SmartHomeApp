package pl.smarthome.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.smarthome.Models.Parameter;

import java.util.List;

public interface ParameterRepository  extends JpaRepository<Parameter, Long> {

    List<Parameter> findAllByComponentId(Long componentId);
}