package niu.itmo.spaceresearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author amifideles
 */
@EqualsAndHashCode(exclude = {"manufacturer", "cabins", "planetTypes"})
@ToString(exclude = {"manufacturer", "cabins", "planetTypes"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "spaceships")
@Entity
public class Spaceship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spaceship_id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "in_expedition", nullable = false)
    private boolean inExpedition;

    @Column(name = "date_of_construction", nullable = false)
    private LocalDate dateOfConstruction;


    @Column(name = "max_speed", nullable = false)
    private BigDecimal maxSpeed;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "current_station_id", nullable = false)
    private Station currentStation;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacture manufacturer;

    @OneToMany(mappedBy = "spaceship")
    private List<Cabins> cabins;

    @ManyToMany
    @JoinTable(
            name = "spaceshipplanettype",
            joinColumns = {@JoinColumn(name = "spaceship_id")},
            inverseJoinColumns = {@JoinColumn(name = "type_id")})
    private Set<PlanetType> planetTypes = new HashSet<>();
}
