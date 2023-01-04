package pl.smarthome.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smarthome.Models.Component;

import java.util.Optional;

public interface ComponentRepository  extends JpaRepository<Component, Long> {
    Optional<Component> findById(Integer id);
    Optional<Component> findByName(String name);
}
