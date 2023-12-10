package niu.itmo.spaceresearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author amifideles
 */
@EqualsAndHashCode(exclude = {"planets"})
@ToString(exclude = {"planets"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "galaxies")
@Entity
public class Galaxy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "galaxy_id")
    private Integer id;
    private String name;
    @Column(name = "discovery_date", nullable = false)
    private LocalDateTime discoveryDate;
    @Column(name = "remote_distance", nullable = false)
    private BigDecimal remoteDistance;
    @OneToMany(mappedBy = "galaxy")
    private List<Planet> planets;
    // Cascade, пробема StackOverflow, equals, hashcode?
}
