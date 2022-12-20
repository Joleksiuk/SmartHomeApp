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
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String category;
    String params;
    String requestMethod;
}
