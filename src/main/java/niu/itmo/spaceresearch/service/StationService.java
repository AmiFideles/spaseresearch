package niu.itmo.spaceresearch.service;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.response.station.DetailedStationDto;
import niu.itmo.spaceresearch.mapper.StationMapper;
import niu.itmo.spaceresearch.model.Station;
import niu.itmo.spaceresearch.repository.StationRepository;
import niu.itmo.spaceresearch.service.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author amifideles
 */
@RequiredArgsConstructor
@Service
public class StationService {
    private final StationRepository stationRepository;

    public List<DetailedStationDto> getAllStations() {
        List<Station> stations = stationRepository.findAll();
        return StationMapper.mapToListDetailedStationDto(stations);
    }

    public DetailedStationDto getStationById(Integer id) {
        Station station = stationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Station not found with id: " + id));
        return StationMapper.mapToDetailedStationDto(station);
    }

}
