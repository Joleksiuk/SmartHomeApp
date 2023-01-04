package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.tuya.TuyaCommand;
import pl.smarthome.Repositories.TuyaCommandRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommandService {

    private final TuyaCommandRepository tuyaCommandRepository;

    public void createCommand(TuyaCommand tuyaCommand) {
        tuyaCommandRepository.save(tuyaCommand);
    }

    public void updateCommand(TuyaCommand tuyaCommand) {
        tuyaCommandRepository.save(tuyaCommand);
    }

    public Optional<TuyaCommand> findCommandById(Integer id) {
        return tuyaCommandRepository.findById(id);
    }

    public List<TuyaCommand> getAllCommands(){
        List<TuyaCommand> tuyaCommands = new LinkedList<>();
        tuyaCommandRepository.findAll().forEach(tuyaCommands::add);
        return tuyaCommands;
    }
}