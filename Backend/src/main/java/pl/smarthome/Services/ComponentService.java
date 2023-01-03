package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.Component;
import pl.smarthome.Repositories.ComponentRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ComponentService {

    private final ComponentRepository componentRepository;

    public void createComponent(Component component) {
        componentRepository.save(component);
    }

    public void updateComponent(Component component) {
        componentRepository.save(component);
    }

    public Optional<Component> findByName(String name){
        return componentRepository.findByName(name);
    }
    public Optional<Component> findComponentById(Integer id) {
        return componentRepository.findById(id);
    }
    public List<Component> getAllComponents(){
        List<Component> components = new LinkedList<>();
        componentRepository.findAll().forEach(components::add);
        return components;
    }
}
