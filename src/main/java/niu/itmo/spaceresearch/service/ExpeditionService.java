package niu.itmo.spaceresearch.service;

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

    Optional<ExpeditionDto> getExpeditionById(Integer id);
}
