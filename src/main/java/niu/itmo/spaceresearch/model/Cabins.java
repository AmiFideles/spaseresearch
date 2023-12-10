package niu.itmo.spaceresearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author amifideles
 */
@EqualsAndHashCode(exclude = {"spaceship", "professions"})
@ToString(exclude = {"spaceship", "professions"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cabins")
@Entity
public class Cabins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cabin_id")
    private Integer id;
    private String name;
    @Column(name = "limited_access", nullable = false)
    private Boolean limitedAccess;
    @Column(name = "volume_cubic_meters", nullable = false)
    private Integer volumeCubicMeters;

    @ManyToOne
    @JoinColumn(name = "spaceship_id")
    private Spaceship spaceship;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "professionscabinassignment",
            joinColumns = {@JoinColumn(name = "profession_id")},
            inverseJoinColumns = {@JoinColumn(name = "cabin_id")})
    private Set<Profession> professions = new HashSet<>();
}
