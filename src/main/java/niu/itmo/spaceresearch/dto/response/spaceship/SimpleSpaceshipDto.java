package niu.itmo.spaceresearch.dto.response.spaceship;

import lombok.Builder;

/**
 * @author amifideles
 */
@Builder
public record SimpleSpaceshipDto(
        Integer id,
        String name
) {
}