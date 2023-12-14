package niu.itmo.spaceresearch.dto;

import niu.itmo.spaceresearch.model.Station;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record SpaceshipDto(
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
        @Nullable
        StationDto currentStation
) {
}
