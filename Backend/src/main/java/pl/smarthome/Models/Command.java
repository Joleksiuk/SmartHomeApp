package pl.smarthome.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.smarthome.Models.ids.CommandId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "commands")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private String value;
    private Long deviceId;
    private Long sceneId;

    public Command(String code, String value, Long deviceId, Long sceneId) {
        this.code = code;
        this.value = value;
        this.deviceId = deviceId;
        this.sceneId = sceneId;
    }
}
