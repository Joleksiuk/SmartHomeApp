package pl.smarthome.Models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPermisson {
    private Long userId;
    private Long deviceId;
    private Boolean canSee;
    private Boolean canControl;
}
