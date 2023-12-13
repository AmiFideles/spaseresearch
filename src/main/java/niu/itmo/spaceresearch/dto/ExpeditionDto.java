package niu.itmo.spaceresearch.dto;

import niu.itmo.spaceresearch.model.ExpeditionStatus;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

public record ExpeditionDto(
        @Nullable
        Integer id,
        ExpeditionStatus status,
        LocalDateTime departureTime,
        @Nullable
        LocalDateTime endTime,
        @Nullable
        ReportDto report,
        StationDto sourceStation,
        StationDto destinationStation,
        List<ResearcherDto> participants,
        SpaceshipDto spaceship
) {
}
