package pl.smarthome.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.smarthome.Models.ids.HouseUserId;

import javax.persistence.*;

@Entity
@Table(name = "role_permission")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long deviceId;
    private String role;
    private String canSee;
    private String canControl;

    public RolePermission(Long deviceId, String role, String canSee, String canControl) {
        this.deviceId = deviceId;
        this.role = role;
        this.canSee = canSee;
        this.canControl = canControl;
    }
}
