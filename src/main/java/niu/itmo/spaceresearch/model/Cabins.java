package niu.itmo.spaceresearch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author amifideles
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cabins")
@Entity
public class Cabins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Boolean limitedAccess;
    private Integer volumeCubicMeters;

}
