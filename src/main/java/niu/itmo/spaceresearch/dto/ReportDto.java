package niu.itmo.spaceresearch.dto;

import java.util.List;

import org.springframework.lang.Nullable;

public record ReportDto(
        @Nullable
        Integer id,
        List<BreakdownDto> breakdowns
) {
}
