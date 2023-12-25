package niu.itmo.spaceresearch.dto;

import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
public record CabinDto(
        @Nullable
        Integer id,
        String name,
        Boolean limitedAccess,
        Integer volumeCubicMeters,
        Boolean currentHasAccess
) {
}
