package niu.itmo.spaceresearch.dto;

import org.springframework.lang.Nullable;

public record CabinDto(
        @Nullable
        Integer id,
        String name,
        Boolean limitedAccess,
        Integer volumeCubicMeters,
        Boolean currentHasAccess
) {
}
