package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.Parameter;
import pl.smarthome.Repositories.ParameterRepository;


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

//    public Optional<Parameter> findParameterById(Long id) {
//        return parameterRepository.findById(id);
//    }
//    public List<Parameter> getAllParameters(){
//        List<Parameter> parameter = new LinkedList<>();
//        parameterRepository.findAll().forEach(parameter::add);
//        return parameter;
//    }
}
