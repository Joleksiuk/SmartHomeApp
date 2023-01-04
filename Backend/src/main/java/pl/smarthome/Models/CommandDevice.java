package pl.smarthome.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "command_device")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommandDevice {

    @Id
    private Long deviceId;
    private Long commandId;

}
