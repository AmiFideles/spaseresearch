package niu.itmo.spaceresearch.repository;

import niu.itmo.spaceresearch.model.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author amifideles
 */
public interface RoleRepository extends JpaRepository<Profession, Long> {
}