package niu.itmo.spaceresearch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author amifideles
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "expeditions")
@Entity
public class Expedition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expedition_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ExpeditionStatus expeditionStatus;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "spaceship_id", nullable = false)
    private Spaceship spaceship;

    @ManyToOne
    @JoinColumn(name = "commander_id", nullable = false)
    private Researcher commander;

    @ManyToOne
    @JoinColumn(name = "source_station_id", nullable = false)
    private Station sourceStation;

    @ManyToOne
    @JoinColumn(name = "destination_station_id", nullable = false)
    private Station destinationStation;

}
