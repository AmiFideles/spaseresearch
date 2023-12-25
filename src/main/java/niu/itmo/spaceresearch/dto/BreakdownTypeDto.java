package niu.itmo.spaceresearch.dto;

import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
public record BreakdownTypeDto(
        @Nullable
        Integer id,
        String name
) {
}
