package niu.itmo.spaceresearch.dto;

import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
public record ManufacturerDto(
        @Nullable
        Integer id,
        String name,
        String country
) {
}
