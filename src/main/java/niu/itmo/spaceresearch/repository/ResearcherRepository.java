package niu.itmo.spaceresearch.repository;

import niu.itmo.spaceresearch.model.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author amifideles
 */

public interface ResearcherRepository extends JpaRepository<Researcher, Integer> {
    Optional<Researcher> findByUsername(String username);

}
