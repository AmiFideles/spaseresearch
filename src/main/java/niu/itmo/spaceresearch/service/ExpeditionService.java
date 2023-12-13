package niu.itmo.spaceresearch.service;

import niu.itmo.spaceresearch.dto.ExpeditionDto;

import java.util.List;

public interface ExpeditionService {
    void createExpedition(
            Integer sourceStationId,
            Integer destinationStationId,
            Integer spaceshipId,
            List<Integer> participantsIds,
            Integer commanderId
    );
    
    List<ExpeditionDto> getAllExpeditions();

    ExpeditionDto getExpeditionById(Integer id);
}
