package niu.itmo.spaceresearch.dto;

import org.springframework.lang.Nullable;

public record BreakdownDto(
        @Nullable
        Integer id,
        String name
) {
}
