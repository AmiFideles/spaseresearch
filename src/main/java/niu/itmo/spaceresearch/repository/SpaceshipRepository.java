package niu.itmo.spaceresearch.repository;

import niu.itmo.spaceresearch.model.Spaceship;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author amifideles
 */
public interface SpaceshipRepository extends JpaRepository<Spaceship, Integer> {

}
