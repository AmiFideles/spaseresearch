package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.response.station.DetailedStationDto;
import niu.itmo.spaceresearch.dto.response.station.SimpleStationDto;
import niu.itmo.spaceresearch.model.Station;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author amifideles
 */
public class StationMapper {
    public static SimpleStationDto mapToSimpleDto(Station station) {
        return SimpleStationDto.builder()
                .id(station.getId())
                .name(station.getName())
                .build();
    }

    public static DetailedStationDto mapToDetailedStationDto(Station station) {
        return DetailedStationDto.builder()
                .id(station.getId())
                .name(station.getName())
                .openingDate(station.getOpeningDate())
                .latitude(station.getLatitude())
                .longitude(station.getLongitude())
                .planet(PlanetMapper.mapToDto(station.getPlanet()))
                .build();
    }

    public static List<DetailedStationDto> mapToListDetailedStationDto(List<Station> stations) {
        return stations.stream().map(StationMapper::mapToDetailedStationDto).collect(Collectors.toList());
    }
}
