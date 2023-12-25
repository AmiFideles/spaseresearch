package niu.itmo.spaceresearch.dto;

import lombok.Builder;

@Builder
public record PlanetTypeDto(
        Integer id,
        String name
) {
}
