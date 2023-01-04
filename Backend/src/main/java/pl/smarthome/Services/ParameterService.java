package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.tuya.Parameter;
import pl.smarthome.Repositories.ParameterRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ParameterService {

    private final ParameterRepository parameterRepository;

    public void createParameter(Parameter parameter) {
        parameterRepository.save(parameter);
    }

    public void updateParameter(Parameter parameter) {
        parameterRepository.save(parameter);
    }

    public Optional<Parameter> findParameterById(Integer id) {
        return parameterRepository.findById(id);
    }
    public List<Parameter> getAllParameters(){
        List<Parameter> parameter = new LinkedList<>();
        parameterRepository.findAll().forEach(parameter::add);
        return parameter;
    }
}
