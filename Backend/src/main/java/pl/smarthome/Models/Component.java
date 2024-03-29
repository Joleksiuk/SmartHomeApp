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
    Long id;
    String name;
    String imagePath;
    String brand;

    public Component(String name, String image,String brand) {
        this.name=name;
        this.imagePath =image;
        this.brand=brand;
    }
}
