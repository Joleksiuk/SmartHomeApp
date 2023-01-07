package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.Parameter;
import pl.smarthome.Repositories.ParameterRepository;

import java.util.List;


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

    public List<Parameter> getAllByComponentId(Long componentId){
        return parameterRepository.findAllByComponentId(componentId);
    }
}
