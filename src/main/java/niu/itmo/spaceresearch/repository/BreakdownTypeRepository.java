package niu.itmo.spaceresearch.repository;

import niu.itmo.spaceresearch.model.BreakdownType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author amifideles
 */
@Repository
public interface BreakdownTypeRepository extends JpaRepository<BreakdownType, Integer> {
}
