package niu.itmo.spaceresearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author amifideles
 */
@EqualsAndHashCode(exclude = {"spaceships", "planetType"})
@ToString(exclude = {"spaceships", "planetType"})
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
    @OneToMany(mappedBy = "planetType")
    private List<Planet> planetType;
    @ManyToMany(mappedBy = "planetTypes")
    private List<Spaceship> spaceships;

}
