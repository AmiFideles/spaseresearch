package niu.itmo.spaceresearch.service;

import niu.itmo.spaceresearch.dto.StationDto;

import java.util.List;

public interface StationService {
    List<StationDto> getAllStations();

    StationDto getStationById(Integer id);
}
