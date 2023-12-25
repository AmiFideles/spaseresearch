package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.PlanetDto;
import niu.itmo.spaceresearch.model.Planet;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author amifideles
 */
public class PlanetMapper {

    public static PlanetDto mapToDto(Planet planet) {
        return PlanetDto.builder()
                .id(planet.getId())
                .name(planet.getName())
                .radius(planet.getRadius())
                .galaxy(GalaxyMapper.mapToDto(planet.getGalaxy()))
                .planetType(PlanetTypeDtoMapper.toPlanetTypeDto(planet.getPlanetType()))
                .build();
    }

    public static List<PlanetDto> mapToDtoList(List<Planet> planets) {
        return planets.stream()
                .map(PlanetMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
