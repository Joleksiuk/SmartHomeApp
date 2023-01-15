package pl.smarthome.Models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.smarthome.Models.Component;
import pl.smarthome.Models.House;
import pl.smarthome.Models.Scene;

import javax.persistence.Access;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseDto {
    private House house;
    private List<Component> components;
    private List<DeviceDto> devices;
    private List<Scene> scenes;
}
