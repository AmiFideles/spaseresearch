package niu.itmo.spaceresearch.repository;

import niu.itmo.spaceresearch.model.Spaceship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author amifideles
 */
@Repository
public interface SpaceshipRepository extends JpaRepository<Spaceship, Integer> {
    // TODO потом переделать условие на FALSE, либо изменить входные данные
    @Query(value = "SELECT s.* FROM Spaceships s " +
            "JOIN Stations st ON s.current_station_id = st.station_id " +
            "JOIN SpaceshipPlanetType spt ON s.spaceship_id = spt.spaceship_id " +
            "JOIN PlanetTypes pt ON spt.type_id = pt.type_id " +
            "WHERE s.in_expedition = false " +
            "AND st.station_id = :sourceStationId " +
            "AND pt.type_id IN (" +
            "    SELECT DISTINCT pt.type_id " +
            "    FROM stations st " +
            "    JOIN planets p ON st.planet_id = p.planet_id " +
            "    JOIN planettypes pt ON p.type_id = pt.type_id " +
            "    JOIN spaceshipplanettype spt ON pt.type_id = spt.type_id " +
            "    WHERE st.station_id = :destinationStationId" +
            ")", nativeQuery = true)
    List<Spaceship> findAvailableShips(@Param("sourceStationId") Integer sourceStationId,
                                       @Param("destinationStationId") Integer destinationStationId);
}
