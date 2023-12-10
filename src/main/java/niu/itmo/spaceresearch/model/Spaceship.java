package niu.itmo.spaceresearch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author amifideles
 */
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
}
