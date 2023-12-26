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

    @ManyToMany(mappedBy = "cabins", fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    private Set<Profession> professions = new HashSet<>();
}
