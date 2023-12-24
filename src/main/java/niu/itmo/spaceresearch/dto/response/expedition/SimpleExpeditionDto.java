package niu.itmo.spaceresearch.dto.response.expedition;

import lombok.Builder;
import niu.itmo.spaceresearch.model.ExpeditionStatus;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

/**
 * @author amifideles
 */
@Builder
public record SimpleExpeditionDto(
        @Nullable
        Integer id,
        ExpeditionStatus status,
        LocalDateTime departureTime,
        @Nullable
        LocalDateTime endTime) {
}