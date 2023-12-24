package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.ExpeditionResponseDto;
import niu.itmo.spaceresearch.dto.ResearcherDto;
import niu.itmo.spaceresearch.model.Expedition;
import niu.itmo.spaceresearch.model.Researcher;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author amifideles
 */
public class ExpeditionMapper {
    ExpeditionResponseDto toResponseDto(Expedition expedition) {
        return ExpeditionResponseDto.builder()
                .id(expedition.getId())
                .status(expedition.getExpeditionStatus())
                .departureTime(expedition.getDepartureTime())
                .endTime(expedition.getEndTime())
                .report(ReportMapper.mapToDto(expedition.getReport()))
                .sourceStation(StationMapper.mapToSimpleDto(expedition.getSourceStation()))
                .destinationStation(StationMapper.mapToSimpleDto(expedition.getDestinationStation()))
                .participants(mapResearchersToDto(expedition.getResearchers()))
                .spaceship(SpaceshipMapper.toSimpleDto(expedition.getSpaceship()))
                .build();
    }

    private List<ResearcherDto> mapResearchersToDto(List<Researcher> researchers) {
        return researchers.stream()
                .map(ResearcherMapper::toDto)
                .collect(Collectors.toList());
    }
}

