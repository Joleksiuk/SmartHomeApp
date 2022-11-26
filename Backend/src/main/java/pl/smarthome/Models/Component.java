package pl.smarthome.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "components", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String image_path;

    public Component(String name, String image) {
        this.name=name;
        this.image_path=image;
    }
}
