package niu.itmo.spaceresearch.dto.response.spaceship;

import lombok.Builder;
import niu.itmo.spaceresearch.dto.CabinDto;
import niu.itmo.spaceresearch.dto.ManufacturerDto;
import niu.itmo.spaceresearch.dto.PlanetTypeDto;
import niu.itmo.spaceresearch.dto.response.station.SimpleStationDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * @author amifideles
 */
@Builder
public record DetailedSpaceshipDto(
        Integer id,
        String name,
        boolean inExpedition,
        LocalDate dateOfConstruction,
        BigDecimal maxSpeed,
        Integer capacity,
        ManufacturerDto manufacturer,
        List<CabinDto> cabins,
        Set<PlanetTypeDto> planetTypes
) {
}
