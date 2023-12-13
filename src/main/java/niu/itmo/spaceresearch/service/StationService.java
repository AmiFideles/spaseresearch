package niu.itmo.spaceresearch.service;

import niu.itmo.spaceresearch.dto.StationDto;

import java.util.List;
import java.util.Optional;

public interface StationService {
    List<StationDto> getAllStations();

    Optional<StationDto> getStationById(Integer id);
}
