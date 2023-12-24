package niu.itmo.spaceresearch.dto.response.station;

import niu.itmo.spaceresearch.dto.PlanetDto;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DetailedStationDto(
        @Nullable
        Integer id,
        String name,
        LocalDate openingDate,
        BigDecimal latitude,
        BigDecimal longitude,
        PlanetDto planet
) {
}
