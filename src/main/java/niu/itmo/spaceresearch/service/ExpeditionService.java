package niu.itmo.spaceresearch.service;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.request.ExpeditionRequestDto;
import niu.itmo.spaceresearch.dto.response.expedition.DetailedExpeditionDto;
import niu.itmo.spaceresearch.dto.response.expedition.SimpleExpeditionDto;
import niu.itmo.spaceresearch.mapper.ExpeditionMapper;
import niu.itmo.spaceresearch.model.*;
import niu.itmo.spaceresearch.repository.ExpeditionRepository;
import niu.itmo.spaceresearch.repository.ResearcherRepository;
import niu.itmo.spaceresearch.repository.SpaceshipRepository;
import niu.itmo.spaceresearch.repository.StationRepository;
import niu.itmo.spaceresearch.service.exceptions.EntityNotFoundException;
import niu.itmo.spaceresearch.service.exceptions.ResearcherNotFound;
import niu.itmo.spaceresearch.service.exceptions.StationNotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
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
    private final SpaceshipRepository spaceshipRepository;

    @Transactional
    public void createExpeditionUsingFunction(ExpeditionRequestDto expeditionRequestDto, Principal principal) {
        Researcher commander = researcherRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResearcherNotFound("Researcher not found username: %s".formatted(principal.getName())));

        Integer expeditionId = expeditionRepository.createExpedition(
                expeditionRequestDto.sourceStationId(),
                expeditionRequestDto.destinationStationId(),
                expeditionRequestDto.spaceshipId(),
                commander.getId()
        );
//        // TODO выкинуть ошибку ?
//        for (Integer researcherId : expeditionRequestDto.researchersId()) {
//            expeditionRepository.addResearcherToExpedition(researcherId, expeditionId);
//        }
    }

    @Transactional
    public void createExpedition(ExpeditionRequestDto expeditionRequestDto, Principal principal) {
        Researcher commander = researcherRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResearcherNotFound("Researcher not found username: %s".formatted(principal.getName())));


        List<Researcher> researchers = researcherRepository.findAllById(expeditionRequestDto.researchersId());
        Station sourceStation = stationRepository.findById(expeditionRequestDto.sourceStationId())
                .orElseThrow(() -> new StationNotFound("Station not found with ID: " + expeditionRequestDto.sourceStationId()));
        Station destinationStation = stationRepository.findById(expeditionRequestDto.destinationStationId())
                .orElseThrow(() -> new StationNotFound("Station not found with ID: " + expeditionRequestDto.destinationStationId()));
        Spaceship spaceship = spaceshipRepository.findById(expeditionRequestDto.spaceshipId())
                .orElseThrow(() -> new EntityNotFoundException("Spaceship not found with ID: " + expeditionRequestDto.spaceshipId()));
        Expedition expedition = Expedition.builder()
                .commander(commander)
                .researchers(researchers)
                .sourceStation(sourceStation)
                .destinationStation(destinationStation)
                .spaceship(spaceship)
                .build();
        expedition.getResearchers().forEach(researcher -> researcher.getExpeditions().add(expedition));
        expedition.setExpeditionStatus(ExpeditionStatus.IN_PROGRESS);
        expedition.setDepartureTime(LocalDateTime.now());
        expeditionRepository.save(expedition);
    }

    public List<SimpleExpeditionDto> getResearcherExpeditions(Principal principal) {
        Researcher researcher = researcherRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResearcherNotFound(
                        "Researcher not found with username: %s"
                                .formatted(principal.getName())));
        List<Expedition> expeditions = expeditionRepository.findExpeditionsByResearcherId(researcher.getId());
        return toListSimpleExpeditionDto(expeditions);
    }

    @Transactional
    public DetailedExpeditionDto getExpeditionById(Integer id, Principal principal) {
        Researcher researcher = researcherRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResearcherNotFound(
                        "Researcher not found with username: %s"
                                .formatted(principal.getName())));

        Expedition expedition = expeditionRepository.findExpeditionById(id)
                .orElseThrow(() -> new EntityNotFoundException("Station not found with ID: " + id));
        boolean isCaptain = expedition.getCommander().equals(researcher);
        return ExpeditionMapper.toDetailedExpeditionDto(expedition, isCaptain);
    }

    public void completeExpedition(int expeditionId) {
        expeditionRepository.setExpeditionCompleted(expeditionId);
    }
}
