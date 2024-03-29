package pl.smarthome.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smarthome.Models.Component;

import java.util.List;
import java.util.Optional;

public interface ComponentRepository  extends JpaRepository<Component, Long> {
    Optional<Component> findById(Long id);
    Optional<Component> findByName(String name);
    List<Component> getAllByBrand(String brand);
}
