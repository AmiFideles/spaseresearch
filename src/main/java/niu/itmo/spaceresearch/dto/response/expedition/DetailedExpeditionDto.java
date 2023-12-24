package niu.itmo.spaceresearch.dto.response.expedition;

import lombok.Builder;
import niu.itmo.spaceresearch.dto.ReportDto;
import niu.itmo.spaceresearch.dto.ResearcherDto;
import niu.itmo.spaceresearch.dto.response.spaceship.SimpleSpaceshipDto;
import niu.itmo.spaceresearch.dto.response.station.SimpleStationDto;
import niu.itmo.spaceresearch.model.ExpeditionStatus;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record DetailedExpeditionDto(
        @Nullable
        Integer id,
        ExpeditionStatus status,
        LocalDateTime departureTime,
        @Nullable
        LocalDateTime endTime,
        @Nullable
        ReportDto report,
        SimpleStationDto sourceStation,
        SimpleStationDto destinationStation,
        List<ResearcherDto> participants,
        SimpleSpaceshipDto spaceship
) {
}
