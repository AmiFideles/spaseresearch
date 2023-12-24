package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.response.station.SimpleStationDto;
import niu.itmo.spaceresearch.model.Station;

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
}
