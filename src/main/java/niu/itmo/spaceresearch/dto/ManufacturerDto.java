package niu.itmo.spaceresearch.dto;

import org.springframework.lang.Nullable;

public record ManufacturerDto(
        @Nullable
        Integer id,
        String name,
        String country
) {
}
