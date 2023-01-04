package pl.smarthome.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "commands")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Scene {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long houseId;
    private Long ownerId;
    private String name;

    Scene(Long houseId,Long ownerId,String name){
        this.houseId=houseId;
        this.ownerId=ownerId;
        this.name=name;
    }
}
