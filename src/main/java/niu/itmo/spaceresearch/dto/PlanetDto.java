package niu.itmo.spaceresearch.dto;

import org.springframework.lang.Nullable;

import java.math.BigDecimal;

public record PlanetDto(
        @Nullable
        Integer id,
        String name,
        BigDecimal radius,
        GalaxyDto galaxy,
        PlanetTypeDto planetType
) {
}
