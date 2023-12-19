package niu.itmo.spaceresearch.service.api;

import niu.itmo.spaceresearch.dto.SpaceshipDto;
import niu.itmo.spaceresearch.model.PlanetType;

import java.util.List;
import java.util.Optional;

public interface SpaceshipService {
    Optional<SpaceshipDto> getSpaceshipById(Integer id);

    List<SpaceshipDto> getSuitableSpaceships(Integer stationId, Integer planetTypeId);
}
