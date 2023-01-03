package pl.smarthome.Models.users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shelly_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShellyUser {
    @Id
    private Integer id;
    private String server;
    private String auth_key;
}
