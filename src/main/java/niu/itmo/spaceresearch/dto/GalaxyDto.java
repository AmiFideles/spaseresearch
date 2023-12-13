package niu.itmo.spaceresearch.dto;

import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GalaxyDto(
        @Nullable
        Integer id,
        String name,
        LocalDateTime discoveryDate,
        BigDecimal remoteDistance
) {
}
