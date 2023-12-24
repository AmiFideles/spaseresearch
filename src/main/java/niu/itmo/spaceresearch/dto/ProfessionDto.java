package niu.itmo.spaceresearch.dto;

import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
public record ProfessionDto(
        @Nullable
        Long id,
        String name
) {
}
