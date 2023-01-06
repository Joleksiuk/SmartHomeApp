package pl.smarthome.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.smarthome.Models.ids.CommandId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "commands")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(CommandId.class)
public class Command implements Serializable {

    private String code;
    private String value;
    @Id
    private Long deviceId;
    @Id
    private Long sceneId;

}
