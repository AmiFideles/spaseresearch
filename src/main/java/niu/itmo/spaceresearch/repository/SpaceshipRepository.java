package niu.itmo.spaceresearch.repository;

import niu.itmo.spaceresearch.model.Spaceship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author amifideles
 */
@Repository
public interface SpaceshipRepository extends JpaRepository<Spaceship, Integer> {

}
