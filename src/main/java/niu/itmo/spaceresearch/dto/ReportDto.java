package niu.itmo.spaceresearch.dto;

import java.util.List;

import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
public record ReportDto(
        @Nullable
        Integer id,
        List<BreakdownTypeDto> breakdowns
) {
}
