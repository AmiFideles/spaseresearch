package niu.itmo.spaceresearch.dto;

import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

@Builder
public record PlanetDto(
        @Nullable
        Integer id,
        String name,
        BigDecimal radius,
        GalaxyDto galaxy,
        PlanetTypeDto planetType
) {
}
