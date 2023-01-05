package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.smarthome.Models.Command;
import pl.smarthome.Models.tuya.TuyaCommand;
import pl.smarthome.Repositories.CommandRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommandService {

    private final CommandRepository commandRepository;
    private final RestTemplate restTemplate;


    public void createCommand(Command command) {
        commandRepository.save(command);
    }

    public void updateCommand(Command command) {
        commandRepository.save(command);
    }

    public Optional<Command> findCommandById(Long id) {
        return commandRepository.findById(id);
    }

    public List<Command> getAllCommandsBySceneId(Long sceneId){
        return new LinkedList<>(commandRepository.getAllBySceneId(sceneId));
    }

    public void setScene(Long sceneId){

        List<Command> commands =  getAllCommandsBySceneId(sceneId);
        commands.stream()
                .map(Command::getEndpoint)
                .forEach(this::makeCommandRequest);

    }

    private final String authKey = "ZWQxZTN1aWQ8C2F82EE9C857CE6FCAE87A3D4103699E42021BC811BA77FDC325A1F52AA8E4EA828527A61F8E75E";

    public String makeCommandRequest(String path){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);

        ResponseEntity<Object> response = restTemplate.exchange(path, HttpMethod.POST, entity, Object.class);
        return response.getBody().toString();
    }
}