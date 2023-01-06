package pl.smarthome.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.smarthome.Models.ids.HouseUserId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "house_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(HouseUserId.class)
public class HouseUser implements Serializable {

    @Id
    private Long userId;
    @Id
    private Long houseId;

    private String role;

}
