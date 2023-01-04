package pl.smarthome.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "house_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HouseUser {

    @Id
    private Long deviceId;
    private Long houseId;
    private String role;

}
