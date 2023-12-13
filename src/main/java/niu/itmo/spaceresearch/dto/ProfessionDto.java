package niu.itmo.spaceresearch.dto;

import org.springframework.lang.Nullable;

public record ProfessionDto(
        @Nullable
        Integer id,
        String name
) {
}
