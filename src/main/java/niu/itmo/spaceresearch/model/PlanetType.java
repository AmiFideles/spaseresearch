package niu.itmo.spaceresearch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author amifideles
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "planettypes")
@Entity
public class PlanetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "galaxy")
    private List<Planet> planetType;
}
