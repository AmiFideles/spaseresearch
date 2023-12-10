package niu.itmo.spaceresearch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author amifideles
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "planets")
@Entity
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planet_id")
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "radius", nullable = false)
    private BigDecimal radius;
    @ManyToOne
    @JoinColumn(name = "galaxy_id", nullable = false)
    private Galaxy galaxy;
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private PlanetType planetType;
    @OneToMany(mappedBy = "planet")
    private List<Planet> stations;
}
