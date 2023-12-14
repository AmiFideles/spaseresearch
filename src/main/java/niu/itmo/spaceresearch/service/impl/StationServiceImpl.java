package niu.itmo.spaceresearch.service.impl;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.StationDto;
import niu.itmo.spaceresearch.repository.StationRepository;
import niu.itmo.spaceresearch.service.api.StationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author amifideles
 */
@RequiredArgsConstructor
@Service
public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository;

    @Override
    public List<StationDto> getAllStations() {
        return stationRepository.findAll().stream().map(it -> new StationDto(
                it.getId(),
                it.getName(),
                it.getOpeningDate(),
                it.getLatitude(),
                it.getLongitude(),
                null
        )).toList();
    }

    @Override
    public Optional<StationDto> getStationById(Integer id) {
        return Optional.empty();
    }
}
