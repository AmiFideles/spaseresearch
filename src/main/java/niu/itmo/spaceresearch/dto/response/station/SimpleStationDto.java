package niu.itmo.spaceresearch.dto.response.station;

import lombok.Builder;
import org.springframework.lang.Nullable;

/**
 * @author amifideles
 */
@Builder
public record SimpleStationDto(
        @Nullable
        Integer id,
        String name
) {
}