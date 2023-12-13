package niu.itmo.spaceresearch.dto;

import org.springframework.lang.Nullable;

import java.util.List;

public record ReportDto(
        @Nullable
        Integer id,
        List<BreakdownDto> breakdowns
) {
}
