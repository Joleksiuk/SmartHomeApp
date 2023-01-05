package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    }
}