package pl.smarthome.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "devices")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Device {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long componentId;
    private Long houseId;
    private String specificId;
    private String name;


}
