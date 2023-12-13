package niu.itmo.spaceresearch.dto;

import org.springframework.lang.Nullable;

public record ManufactureDto(
        @Nullable
        Integer id,
        String name,
        String country
) {
}
