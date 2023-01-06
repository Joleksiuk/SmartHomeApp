package pl.smarthome.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.smarthome.Models.Parameter;

public interface ParameterRepository  extends JpaRepository<Parameter, Long> {

}