package niu.itmo.spaceresearch.repository;

import niu.itmo.spaceresearch.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author amifideles
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}