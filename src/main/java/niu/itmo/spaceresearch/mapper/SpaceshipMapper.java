package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.SpaceshipDto;
import niu.itmo.spaceresearch.model.Spaceship;

/**
 * @author amifideles
 */
public class SpaceshipMapper {

    public static SpaceshipDto toSimpleDto(Spaceship spaceship) {
        return SpaceshipDto.builder()
                .id(spaceship.getId())
                .name(spaceship.getName())
                .build();
    }
}
