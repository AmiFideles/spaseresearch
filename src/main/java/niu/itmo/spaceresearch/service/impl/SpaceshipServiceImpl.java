package niu.itmo.spaceresearch.service.impl;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.SpaceshipDto;
import niu.itmo.spaceresearch.repository.SpaceshipRepository;
import niu.itmo.spaceresearch.service.api.SpaceshipService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author amifideles
 */
@RequiredArgsConstructor
@Service
public class SpaceshipServiceImpl implements SpaceshipService {
    private final SpaceshipRepository spaceshipRepository;


    @Override
    public Optional<SpaceshipDto> getSpaceshipById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<SpaceshipDto> getSuitableSpaceships(Integer stationId, Integer planetTypeId) {
        return null;
    }
}
