package pl.smarthome.Models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.smarthome.Models.RolePermission;

@AllArgsConstructor
@Getter
@Setter
public class RolePermissionDto {
    private Long id;
    private Long deviceId;
    private String deviceName;
    private String role;
    private String canSee;
    private String canControl;

    public RolePermissionDto(RolePermission rolePermission, String deviceName){
        this.canControl=rolePermission.getCanControl();
        this.canSee=rolePermission.getCanSee();
        this.role=rolePermission.getRole();
        this.deviceId=rolePermission.getDeviceId();
        this.deviceName=deviceName;
        this.deviceId =rolePermission.getDeviceId();
        this.id=rolePermission.getId();
    }
}
