package niu.itmo.spaceresearch.dto.request;

import java.util.List;

/**
 * @author amifideles
 */
public record ExpeditionRequestDto(
        Integer sourceStationId,
        Integer destinationStationId,
        Integer spaceshipId,
        List<Integer> participants
) {
}