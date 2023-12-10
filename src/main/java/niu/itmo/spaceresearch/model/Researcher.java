package niu.itmo.spaceresearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author amifideles
 */
@EqualsAndHashCode(exclude = {"expeditions", "professions"})
@ToString(exclude = {"expeditions", "professions"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "researchers")
public class Researcher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "researcher_id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "in_expedition", nullable = false)
    private Boolean inExpedition;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Sex sex;

    @ManyToMany
    @JoinTable(
            name = "expeditionresearchers",
            joinColumns = {@JoinColumn(name = "expedition_id")},
            inverseJoinColumns = {@JoinColumn(name = "researcher_id")})
    private Set<Expedition> expeditions = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "researcherprofessions",
            joinColumns = {@JoinColumn(name = "researcher_id")},
            inverseJoinColumns = {@JoinColumn(name = "profession_id")})
    private Set<Profession> professions = new HashSet<>();


}
