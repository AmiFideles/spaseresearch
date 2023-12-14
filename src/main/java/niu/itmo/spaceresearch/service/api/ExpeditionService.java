package niu.itmo.spaceresearch.service.api;

import niu.itmo.spaceresearch.dto.ExpeditionDto;

import java.util.List;
import java.util.Optional;

public interface ExpeditionService {
    void createExpedition(
            Integer sourceStationId,
            Integer destinationStationId,
            Integer spaceshipId,
            List<Integer> participantsIds,
            Integer commanderId
    );

    List<ExpeditionDto> getAllExpeditions();

    List<ExpeditionDto> getResearcherExpeditions(Integer researcherId);

    Optional<ExpeditionDto> getExpeditionById(Integer id);
}
