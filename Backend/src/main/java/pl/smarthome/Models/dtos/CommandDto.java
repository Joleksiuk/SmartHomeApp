package pl.smarthome.Models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CommandDto {
    private Long deviceId;
    private String code;
    private String value;
}
