package pl.smarthome.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "instructions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Instruction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long deviceId;
    private Long sceneId;
    private String endpoint;

    Instruction(Long deviceId, String endpoint){
        this.deviceId=deviceId;
        this.endpoint=endpoint;
    }
}
