package pl.smarthome.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parameters")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Parameter {
    @Id
    private Long id;
    private String code;
    private String value;
    private String valueType;
    private Long componentId;
}
