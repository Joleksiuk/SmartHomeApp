package pl.smarthome.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smarthome.Models.Command;
import pl.smarthome.Models.ids.CommandId;
import pl.smarthome.Models.ids.HouseUserId;

import java.util.List;
import java.util.Optional;

public interface CommandRepository  extends JpaRepository<Command, CommandId> {

    List<Command> getAllBySceneId(Long sceneId);
}
