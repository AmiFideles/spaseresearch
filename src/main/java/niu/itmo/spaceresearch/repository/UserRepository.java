package niu.itmo.spaceresearch.repository;

import niu.itmo.spaceresearch.model.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author amifideles
 */

public interface UserRepository extends JpaRepository<Researcher, Long> {
    Researcher findByEmail(String email);
}