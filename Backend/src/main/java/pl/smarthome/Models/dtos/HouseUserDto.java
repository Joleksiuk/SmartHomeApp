package pl.smarthome.Models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class HouseUserDto {

    private Long userId;
    private Long houseId;
    private String username;
    private String email;
    private String role;
}
