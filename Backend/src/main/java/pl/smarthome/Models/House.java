package pl.smarthome.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "houses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class House {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long ownerId;
    private String name;

    House(Long ownerId, String name){
        this.ownerId=ownerId;
        this.name=name;
    }

}
