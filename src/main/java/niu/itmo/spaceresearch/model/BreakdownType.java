package niu.itmo.spaceresearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author amifideles
 */
@EqualsAndHashCode(exclude = {"reports"})
@ToString(exclude = {"reports"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "breakdowntypes")
@Entity
public class BreakdownType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "breakdown_type_id")
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToMany(mappedBy = "breakdownTypes")
    private List<Report> reports;
}
