package niu.itmo.spaceresearch.service.api;

import niu.itmo.spaceresearch.model.Spaceship;
import org.springframework.stereotype.Service;

/**
 * @author amifideles
 */
@Service
public interface SpaceshipService {
    Spaceship getSpaceshipById(Integer id);
}
