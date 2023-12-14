package niu.itmo.spaceresearch.repository;

import niu.itmo.spaceresearch.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author amifideles
 */
@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
}
