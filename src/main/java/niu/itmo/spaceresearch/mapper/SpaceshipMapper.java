package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.DetailedSpaceshipDto;
import niu.itmo.spaceresearch.dto.response.spaceship.SimpleSpaceshipDto;
import niu.itmo.spaceresearch.model.Spaceship;

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
//                .manufacturer(spaceship.getManufacturer())
//                .cabins(spaceship.getCabins())
                .build();
    }

    public static SimpleSpaceshipDto toSimpleSpaceshipDto(Spaceship spaceship) {
        return SimpleSpaceshipDto.builder()
                .id(spaceship.getId())
                .name(spaceship.getName())
                .build();
    }

}
