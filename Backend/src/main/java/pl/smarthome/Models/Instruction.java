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
    InstructionId id;
    public String code;
    public String value;

}
