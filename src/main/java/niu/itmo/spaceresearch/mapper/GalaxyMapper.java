package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.GalaxyDto;
import niu.itmo.spaceresearch.model.Galaxy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author amifideles
 */
public class GalaxyMapper {

    public static GalaxyDto mapToDto(Galaxy galaxy) {
        return GalaxyDto.builder()
                .id(galaxy.getId())
                .name(galaxy.getName())
                .discoveryDate(galaxy.getDiscoveryDate())
                .remoteDistance(galaxy.getRemoteDistance())
                .build();
    }

    public static List<GalaxyDto> mapToDtoList(List<Galaxy> galaxies) {
        return galaxies.stream()
                .map(GalaxyMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
