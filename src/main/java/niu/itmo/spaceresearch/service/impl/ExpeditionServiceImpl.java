package niu.itmo.spaceresearch.service.impl;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.ExpeditionResponseDto;
import niu.itmo.spaceresearch.dto.request.ExpeditionRequestDto;
import niu.itmo.spaceresearch.model.Expedition;
import niu.itmo.spaceresearch.model.Researcher;
import niu.itmo.spaceresearch.model.Station;
import niu.itmo.spaceresearch.repository.ExpeditionRepository;
import niu.itmo.spaceresearch.repository.ResearcherRepository;
import niu.itmo.spaceresearch.repository.StationRepository;
import niu.itmo.spaceresearch.service.api.ExpeditionService;
import niu.itmo.spaceresearch.service.exceptions.ResearcherNotFound;
import niu.itmo.spaceresearch.service.exceptions.StationNotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author amifideles
 */
@Service
@RequiredArgsConstructor
public class ExpeditionServiceImpl implements ExpeditionService {
    private final ExpeditionRepository expeditionRepository;
    private final ResearcherRepository researcherRepository;
    private final StationRepository stationRepository;

    @Transactional
    @Override
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

    @Override
    public void createExpedition(ExpeditionRequestDto expeditionRequestDto) {
        List<Researcher> researchers = researcherRepository.findAllById(expeditionRequestDto.researchersId());
        Researcher commander = researcherRepository.findById(expeditionRequestDto.commanderId())
                .orElseThrow(() -> new ResearcherNotFound("Feedback not found with ID: %d" .formatted(expeditionRequestDto.commanderId())));
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

    @Override
    public List<ExpeditionResponseDto> getAllExpeditions() {
        return null;
    }

    @Override
    public List<ExpeditionResponseDto> getResearcherExpeditions(Integer researcherId) {
        return null;
    }

    @Override
    public Optional<ExpeditionResponseDto> getExpeditionById(Integer id) {
        return Optional.empty();
    }
}
