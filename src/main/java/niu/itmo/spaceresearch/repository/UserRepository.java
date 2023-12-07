package niu.itmo.spaceresearch.repository;

import niu.itmo.spaceresearch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author amifideles
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}