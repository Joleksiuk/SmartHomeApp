package pl.smarthome.Models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.smarthome.Controllers.tuya.details.CodeValue;
import pl.smarthome.Models.Device;
import pl.smarthome.Models.RolePermission;

import java.lang.reflect.Member;
import java.util.List;

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
    private List<CodeValue> props;
    private List<RolePermission> permissions;

    public DeviceDto(Device device, String componentName, String imagePath, String brand, List<CodeValue> props, List<RolePermission> permissions){
        this.id = device.getId();
        this.componentId = device.getComponentId();
        this.houseId=device.getHouseId();
        this.specificId=device.getSpecificId();
        this.name=device.getName();
        this.componentName=componentName;
        this.imagePath=imagePath;
        this.brand=brand;
        this.props=props;
        this.permissions=permissions;
    }
    @Override
    public boolean equals(Object anObject) {
        if (!(anObject instanceof Member)) {
            return false;
        }
        DeviceDto otherMember = (DeviceDto)anObject;
        return otherMember.getId().equals(getId());
    }

}
