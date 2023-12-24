package niu.itmo.spaceresearch.service.api;

import niu.itmo.spaceresearch.dto.response.station.DetailedStationDto;

import java.util.List;
import java.util.Optional;

public interface StationService {
    List<DetailedStationDto> getAllStations();

    Optional<DetailedStationDto> getStationById(Integer id);
}
