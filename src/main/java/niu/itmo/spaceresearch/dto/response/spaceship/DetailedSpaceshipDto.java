package niu.itmo.spaceresearch.dto.response.spaceship;

import lombok.Builder;
import niu.itmo.spaceresearch.dto.CabinDto;
import niu.itmo.spaceresearch.dto.ManufacturerDto;
import niu.itmo.spaceresearch.dto.PlanetTypeDto;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
public record DetailedSpaceshipDto(
        @Nullable
        Integer id,
        String name,
        LocalDate dateOfConstruction,
        BigDecimal maxSpeed,
        Integer capacity,
        ManufacturerDto manufacturer,
        List<CabinDto> cabins,
        @Nullable
        Boolean inExpedition,
        Set<PlanetTypeDto> planetTypes
) {
}
