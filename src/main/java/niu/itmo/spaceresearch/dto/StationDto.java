package niu.itmo.spaceresearch.dto;

import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StationDto(
        @Nullable
        Integer id,
        String name,
        LocalDate openingDate,
        BigDecimal latitude,
        BigDecimal longitude,
        PlanetDto planet
) {
}
