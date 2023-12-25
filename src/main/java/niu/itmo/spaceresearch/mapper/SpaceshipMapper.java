package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.response.spaceship.DetailedSpaceshipDto;
import niu.itmo.spaceresearch.dto.response.spaceship.SimpleSpaceshipDto;
import niu.itmo.spaceresearch.model.Spaceship;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author amifideles
 */
public class SpaceshipMapper {

    public static DetailedSpaceshipDto toDetailedSpaceshipDto(Spaceship spaceship) {
        return DetailedSpaceshipDto.builder()
                .id(spaceship.getId())
                .name(spaceship.getName())
                .dateOfConstruction(spaceship.getDateOfConstruction())
                .maxSpeed(spaceship.getMaxSpeed())
                .capacity(spaceship.getCapacity())
                .inExpedition(spaceship.isInExpedition())
                .manufacturer(ManufacturerMapper.toManufacturerDto(spaceship.getManufacturer()))
                .cabins(CabinsMapper.toCabinDtoList(spaceship.getCabins()))
                .planetTypes(PlanetTypeDtoMapper.toPlanetTypeDtoList(spaceship.getPlanetTypes()))
                .build();
    }

    public static List<DetailedSpaceshipDto> toListDetailedSpaceshipDtoList(List<Spaceship> spaceships) {
        return spaceships.stream().map(SpaceshipMapper::toDetailedSpaceshipDto).collect(Collectors.toList());
    }

    public static SimpleSpaceshipDto toSimpleSpaceshipDto(Spaceship spaceship) {
        return SimpleSpaceshipDto.builder()
                .id(spaceship.getId())
                .name(spaceship.getName())
                .build();
    }

}
