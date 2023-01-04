package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.tuya.TuyaCommand;
import pl.smarthome.Repositories.CommandRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommandService {

    private final CommandRepository commandRepository;

    public void createCommand(TuyaCommand tuyaCommand) {
        commandRepository.save(tuyaCommand);
    }

    public void updateCommand(TuyaCommand tuyaCommand) {
        commandRepository.save(tuyaCommand);
    }

    public Optional<TuyaCommand> findCommandById(Integer id) {
        return commandRepository.findById(id);
    }

    public List<TuyaCommand> getAllCommands(){
        List<TuyaCommand> tuyaCommands = new LinkedList<>();
        commandRepository.findAll().forEach(tuyaCommands::add);
        return tuyaCommands;
    }
}