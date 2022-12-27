package pl.smarthome.Models.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tuya_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TuyaUser {
    @Id
    private Long id;
    private String server;
    private String accessId;
    private String secretKey;
}
