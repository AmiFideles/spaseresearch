package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.ResearcherDto;
import niu.itmo.spaceresearch.dto.response.expedition.DetailedExpeditionDto;
import niu.itmo.spaceresearch.dto.response.expedition.SimpleExpeditionDto;
import niu.itmo.spaceresearch.model.Expedition;
import niu.itmo.spaceresearch.model.Researcher;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author amifideles
 */
public class ExpeditionMapper {
    // TODO засетить значение isCapitan?
    public static DetailedExpeditionDto toDetailedExpeditionDto(Expedition expedition, Boolean isCaptain) {
        return DetailedExpeditionDto.builder()
                .id(expedition.getId())
                .status(expedition.getExpeditionStatus())
                .departureTime(expedition.getDepartureTime())
                .endTime(expedition.getEndTime())
                .report(expedition.getReport()==null?null:ReportMapper.mapToDto(expedition.getReport()))
                .sourceStation(StationMapper.mapToSimpleDto(expedition.getSourceStation()))
                .destinationStation(StationMapper.mapToSimpleDto(expedition.getDestinationStation()))
                .participants(mapResearchersToDto(expedition.getResearchers()))
                .spaceship(SpaceshipMapper.toSimpleSpaceshipDto(expedition.getSpaceship()))
                .isCapitan(isCaptain)
                .build();
    }

    public static SimpleExpeditionDto toSimpleExpeditionDto(Expedition expedition) {
        return SimpleExpeditionDto.builder()
                .id(expedition.getId())
                .status(expedition.getExpeditionStatus())
                .departureTime(expedition.getDepartureTime())
                .endTime(expedition.getEndTime())
                .build();
    }

    public static List<SimpleExpeditionDto> toListSimpleExpeditionDto(List<Expedition> expeditions) {
        return expeditions.stream().map(ExpeditionMapper::toSimpleExpeditionDto).collect(Collectors.toList());
    }

    private static List<ResearcherDto> mapResearchersToDto(List<Researcher> researchers) {
        return researchers.stream()
                .map(ResearcherMapper::toResearcherDto)
                .collect(Collectors.toList());
    }
}

