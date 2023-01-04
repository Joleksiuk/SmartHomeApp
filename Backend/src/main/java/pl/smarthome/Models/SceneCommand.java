package pl.smarthome.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "scene_command")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SceneCommand {

    @Id
    private Long sceneId;
    private Long commandId;

}
