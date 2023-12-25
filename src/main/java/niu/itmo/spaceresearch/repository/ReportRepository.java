package niu.itmo.spaceresearch.repository;

import niu.itmo.spaceresearch.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author amifideles
 */
@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
}
