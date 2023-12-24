package niu.itmo.spaceresearch.service.impl;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.DetailedSpaceshipDto;
import niu.itmo.spaceresearch.model.Spaceship;
import niu.itmo.spaceresearch.repository.SpaceshipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author amifideles
 */

@RequiredArgsConstructor
@Service
public class SpaceshipService {
    private final SpaceshipRepository spaceshipRepository;


    public DetailedSpaceshipDto getSpaceshipById(Integer id) {
         return null;
    }

    public List<DetailedSpaceshipDto> getSuitableSpaceships(Integer stationId, Integer planetTypeId) {
        return null;
    }
}
