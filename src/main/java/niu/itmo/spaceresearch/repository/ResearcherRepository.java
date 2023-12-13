package niu.itmo.spaceresearch.repository;

import niu.itmo.spaceresearch.model.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author amifideles
 */

public interface ResearcherRepository extends JpaRepository<Researcher, Long> {
    Optional<Researcher> findByUsername(String username);
}
