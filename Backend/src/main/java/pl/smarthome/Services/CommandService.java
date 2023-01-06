package pl.smarthome.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.smarthome.Models.Command;
import pl.smarthome.Models.CommandDto;
import pl.smarthome.Models.ids.CommandId;
import pl.smarthome.Repositories.CommandRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Command> getAllCommandsBySceneId(Long sceneId){
        return commandRepository.getAllBySceneId(sceneId);
    }

    private CommandDto commandToDto(Command command){
        return new CommandDto(command.getDeviceId(),command.getCode(),command.getValue());
    }

    public List<CommandDto> getAllCommandsDtoByIds(Long sceneId, Long deviceId){
        List<Command> commands = commandRepository.getAllBySceneId(sceneId);
        return commands.stream()
                .filter(command -> (deviceId==command.getDeviceId()))
                .map(this::commandToDto)
                .collect(Collectors.toList());
    }

}
