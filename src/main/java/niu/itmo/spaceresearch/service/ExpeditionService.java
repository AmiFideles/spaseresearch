package niu.itmo.spaceresearch.service;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.request.ExpeditionRequestDto;
import niu.itmo.spaceresearch.dto.response.expedition.DetailedExpeditionDto;
import niu.itmo.spaceresearch.dto.response.expedition.SimpleExpeditionDto;
import niu.itmo.spaceresearch.mapper.ExpeditionMapper;
import niu.itmo.spaceresearch.model.Expedition;
import niu.itmo.spaceresearch.model.Researcher;
import niu.itmo.spaceresearch.model.Station;
import niu.itmo.spaceresearch.repository.ExpeditionRepository;
import niu.itmo.spaceresearch.repository.ResearcherRepository;
import niu.itmo.spaceresearch.repository.StationRepository;
import niu.itmo.spaceresearch.service.exceptions.EntityNotFoundException;
import niu.itmo.spaceresearch.service.exceptions.ResearcherNotFound;
import niu.itmo.spaceresearch.service.exceptions.StationNotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

import static niu.itmo.spaceresearch.mapper.ExpeditionMapper.toListSimpleExpeditionDto;

/**
 * @author amifideles
 */
@Service
@RequiredArgsConstructor
public class ExpeditionService {
    private final ExpeditionRepository expeditionRepository;
    private final ResearcherRepository researcherRepository;
    private final StationRepository stationRepository;

    @Transactional
    public void createExpeditionUsingFunction(ExpeditionRequestDto expeditionRequestDto) {
        Integer expeditionId = expeditionRepository.createExpedition(
                expeditionRequestDto.sourceStationId(),
                expeditionRequestDto.destinationStationId(),
                expeditionRequestDto.spaceshipId(),
                expeditionRequestDto.commanderId()
        );
//        // TODO выкинуть ошибку ?
//        for (Integer researcherId : expeditionRequestDto.researchersId()) {
//            expeditionRepository.addResearcherToExpedition(researcherId, expeditionId);
//        }
    }

    public void createExpedition(ExpeditionRequestDto expeditionRequestDto, Principal principal) {
        Researcher changeCommander = researcherRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResearcherNotFound("Researcher not found with ID: %d".formatted(expeditionRequestDto.commanderId())));
        Integer commanderId = changeCommander.getId();

        List<Researcher> researchers = researcherRepository.findAllById(expeditionRequestDto.researchersId());
        Researcher commander = researcherRepository.findById(expeditionRequestDto.commanderId())
                .orElseThrow(() -> new ResearcherNotFound("Researcher not found with ID: %d".formatted(expeditionRequestDto.commanderId())));
        Station sourceStation = stationRepository.findById(expeditionRequestDto.sourceStationId())
                .orElseThrow(() -> new StationNotFound("Station not found with ID: " + expeditionRequestDto.sourceStationId()));
        Station destinationStation = stationRepository.findById(expeditionRequestDto.destinationStationId())
                .orElseThrow(() -> new StationNotFound("Station not found with ID: " + expeditionRequestDto.destinationStationId()));
        Expedition expedition = Expedition.builder()
                .commander(commander)
                .researchers(researchers)
                .sourceStation(sourceStation)
                .destinationStation(destinationStation)
                .build();
        expeditionRepository.save(expedition);
    }

    public List<SimpleExpeditionDto> getResearcherExpeditions(Principal principal) {
        Researcher researcher = researcherRepository.findByUsername(principal.getName()).orElseThrow(() -> new ResearcherNotFound("Researcher not found with username: %s".formatted(principal.getName())));
        List<Expedition> expeditions = expeditionRepository.findExpeditionsByResearcherId(researcher.getId());
        return toListSimpleExpeditionDto(expeditions);
    }

    public DetailedExpeditionDto getExpeditionById(Integer id) {
        Expedition expedition = expeditionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Station not found with ID: " + id));
        return ExpeditionMapper.toDetailedExpeditionDto(expedition);
    }
}
