package niu.itmo.spaceresearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author amifideles
 */
@EqualsAndHashCode(exclude = {"expedition", "breakdownTypes"})
@ToString(exclude = {"expedition", "breakdownTypes"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reports")
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Integer id;
    @Column(name = "description")
    private String description;
    @OneToOne
    @JoinColumn(name = "expedition_id", unique = true, nullable = false)
    private Expedition expedition;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "reportsbreakdowns",
            joinColumns = {@JoinColumn(name = "report_id")},
            inverseJoinColumns = {@JoinColumn(name = "breakdown_type_id")})
    private Set<BreakdownType> breakdownTypes = new HashSet<>();
}
