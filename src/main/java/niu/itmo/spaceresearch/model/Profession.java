package niu.itmo.spaceresearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author amifideles
 */
@EqualsAndHashCode(exclude = {"researchersId", "cabins"})
@ToString(exclude = {"researchersId", "cabins"})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "professions")
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profession_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "professions")
    private List<Researcher> researchers;

    @ManyToMany(mappedBy = "professions")
    private List<Cabins> cabins;
}
