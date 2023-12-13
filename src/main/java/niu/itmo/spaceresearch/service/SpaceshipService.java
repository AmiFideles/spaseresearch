package niu.itmo.spaceresearch.service;

import niu.itmo.spaceresearch.dto.SpaceshipDto;
import niu.itmo.spaceresearch.model.PlanetType;

import java.util.List;
import java.util.Optional;

public interface SpaceshipService {
    Optional<SpaceshipDto> getSpaceshipById(Integer id);

    List<SpaceshipDto> getSuitableSpaceship(Integer stationId, PlanetType planetType);
}
