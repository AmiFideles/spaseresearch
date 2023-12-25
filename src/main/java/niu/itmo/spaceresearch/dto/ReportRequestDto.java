package niu.itmo.spaceresearch.dto;

import java.util.List;

/**
 * @author amifideles
 */

public record ReportRequestDto(
        Integer expeditionId,
        List<BreakdownTypeDto> breakdowns
) {
}