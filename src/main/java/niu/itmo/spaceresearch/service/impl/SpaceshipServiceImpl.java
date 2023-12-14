package niu.itmo.spaceresearch.service.impl;

import niu.itmo.spaceresearch.dto.SpaceshipDto;
import niu.itmo.spaceresearch.model.PlanetType;
import niu.itmo.spaceresearch.model.Spaceship;
import niu.itmo.spaceresearch.service.SpaceshipService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author amifideles
 */
@Service
public class SpaceshipServiceImpl implements SpaceshipService {


    @Override
    public Optional<SpaceshipDto> getSpaceshipById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<SpaceshipDto> getSuitableSpaceships(Integer stationId, PlanetType planetType) {
        return null;
    }
}
