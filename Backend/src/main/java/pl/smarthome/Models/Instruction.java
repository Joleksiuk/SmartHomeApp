package pl.smarthome.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.smarthome.Models.ids.InstructionId;

import javax.persistence.*;

@Entity
@Table(name = "instructions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Instruction {

    @Id
    InstructionId id;
    public String code;
    public String value;

}
