package niu.itmo.spaceresearch.service.mock;

import lombok.extern.slf4j.Slf4j;
import niu.itmo.spaceresearch.dto.ExpeditionDto;
import niu.itmo.spaceresearch.model.ExpeditionStatus;
import niu.itmo.spaceresearch.service.api.ExpeditionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ExpeditionServiceMock implements ExpeditionService {
    private static ExpeditionDto mockExpedition(Integer id) {
        return new ExpeditionDto(
                id,
                ExpeditionStatus.IN_PROGRESS,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                StationServiceMock.mockStation(id),
                StationServiceMock.mockStation(id + 1),
                List.of(),
                SpaceshipServiceMock.mockSpaceship(id)
        );
    }

    @Override
    public void createExpedition(Integer sourceStationId, Integer destinationStationId, Integer spaceshipId, List<Integer> participantsIds, Integer commanderId) {
        log.info("Created expedition: %s %s %s %s %s".formatted(
                sourceStationId,
                destinationStationId,
                spaceshipId,
                participantsIds,
                commanderId
        ));
    }

    @Override
    public List<ExpeditionDto> getAllExpeditions() {
        return List.of(
                mockExpedition(1),
                mockExpedition(2),
                mockExpedition(3)
        );
    }

    @Override
    public List<ExpeditionDto> getResearcherExpeditions(Integer researcherId) {
        return List.of(
                mockExpedition(1),
                mockExpedition(2),
                mockExpedition(3)
        );
    }

    @Override
    public Optional<ExpeditionDto> getExpeditionById(Integer id) {
        return Optional.of(mockExpedition(id));
    }
}
