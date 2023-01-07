package pl.smarthome.Models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.smarthome.Models.Device;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeviceDto {
    private Long id;
    private Long componentId;
    private Long houseId;
    private String specificId;
    private String name;
    private String componentName;
    private String imagePath;
    private String brand;
    private String props;

    public DeviceDto(Device device, String componentName, String imagePath, String brand, String props){
        this.id = device.getId();
        this.componentId = device.getComponentId();
        this.houseId=device.getHouseId();
        this.specificId=device.getSpecificId();
        this.name=device.getName();
        this.componentName=componentName;
        this.imagePath=imagePath;
        this.brand=brand;
        this.props=props;
    }

}
