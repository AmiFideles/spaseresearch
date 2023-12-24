package niu.itmo.spaceresearch.dto;

import lombok.Builder;
import niu.itmo.spaceresearch.dto.response.station.DetailedStationDto;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
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
        DetailedStationDto currentStation
) {
}
