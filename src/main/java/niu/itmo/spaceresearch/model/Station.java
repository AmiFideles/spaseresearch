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
@Table(name = "stations")
@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "opening_date", nullable = false)
    private LocalDate openingDate;
    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude;
    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude;
    @ManyToOne
    @JoinColumn(name = "planet_id", nullable = false)
    private Planet planet;

}
