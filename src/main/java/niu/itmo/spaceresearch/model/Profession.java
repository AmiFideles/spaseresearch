package niu.itmo.spaceresearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author amifideles
 */
@EqualsAndHashCode(exclude = {"researchers", "cabins"})
@ToString(exclude = {"researchers", "cabins"})
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

    @ManyToMany(mappedBy = "professions", fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    private List<Researcher> researchers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    @JoinTable(
            name = "professionscabinassignment",
            joinColumns = {@JoinColumn(name = "profession_id")},
            inverseJoinColumns = {@JoinColumn(name = "cabin_id")})
    private List<Cabins> cabins;
}
