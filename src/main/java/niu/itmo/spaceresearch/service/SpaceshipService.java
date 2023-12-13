package niu.itmo.spaceresearch.service;

import niu.itmo.spaceresearch.dto.SpaceshipDto;
import niu.itmo.spaceresearch.model.PlanetType;

import java.util.List;

public interface SpaceshipService {
    SpaceshipDto getSpaceshipById(Integer id);

    List<SpaceshipDto> getSuitableSpaceship(Integer stationId, PlanetType planetType);
}
