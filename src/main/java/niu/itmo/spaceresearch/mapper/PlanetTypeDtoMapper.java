package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.PlanetTypeDto;
import niu.itmo.spaceresearch.model.PlanetType;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author amifideles
 */
public class PlanetTypeDtoMapper {
    public static PlanetTypeDto toPlanetTypeDto(PlanetType planetType) {
        return PlanetTypeDto.builder()
                .id(planetType.getId())
                .name(planetType.getName())
                .build();
    }

    public static Set<PlanetTypeDto> toPlanetTypeDtoList(Set<PlanetType> planetTypes) {
        return planetTypes.stream().map(PlanetTypeDtoMapper::toPlanetTypeDto).collect(Collectors.toSet());
    }
}
