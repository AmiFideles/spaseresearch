package niu.itmo.spaceresearch.repository;

import niu.itmo.spaceresearch.model.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author amifideles
 */

public interface ResearcherRepository extends JpaRepository<Researcher, Integer> {
    Optional<Researcher> findByUsername(String username);
    @Query("SELECT r FROM Researcher r WHERE r.inExpedition = false ")
    List<Researcher> findFreeResearchers();
}
