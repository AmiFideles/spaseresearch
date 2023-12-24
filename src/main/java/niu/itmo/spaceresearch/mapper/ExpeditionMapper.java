package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.response.expedition.DetailedExpeditionDto;
import niu.itmo.spaceresearch.dto.ResearcherDto;
import niu.itmo.spaceresearch.dto.response.expedition.SimpleExpeditionDto;
import niu.itmo.spaceresearch.model.Expedition;
import niu.itmo.spaceresearch.model.Researcher;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author amifideles
 */
public class ExpeditionMapper {
    public static DetailedExpeditionDto toDetailedExpeditionDto(Expedition expedition) {
        return DetailedExpeditionDto.builder()
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

    public static SimpleExpeditionDto toSimpleExpeditionDto(Expedition expedition){
        return SimpleExpeditionDto.builder()
                .id(expedition.getId())
                .status(expedition.getExpeditionStatus())
                .departureTime(expedition.getDepartureTime())
                .endTime(expedition.getEndTime())
                .build();
    }



    private static List<ResearcherDto> mapResearchersToDto(List<Researcher> researchers) {
        return researchers.stream()
                .map(ResearcherMapper::toDto)
                .collect(Collectors.toList());
    }
}

