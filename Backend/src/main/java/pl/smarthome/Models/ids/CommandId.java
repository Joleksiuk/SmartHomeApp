package pl.smarthome.Models.ids;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommandId implements Serializable {
    public Long deviceId;
    public Long sceneId;
}
