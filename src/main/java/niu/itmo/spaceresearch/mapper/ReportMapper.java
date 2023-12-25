package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.BreakdownTypeDto;
import niu.itmo.spaceresearch.dto.ReportDto;
import niu.itmo.spaceresearch.model.BreakdownType;
import niu.itmo.spaceresearch.model.Report;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author amifideles
 */
public class ReportMapper {
    public static ReportDto mapToDto(Report report) {
        return ReportDto.builder()
                .id(report.getId())
                .breakdowns(mapBreakdownsToDto(report.getBreakdownTypes()))
                .build();
    }

    private static List<BreakdownTypeDto> mapBreakdownsToDto(Set<BreakdownType> breakdownTypes) {
        return breakdownTypes.stream()
                .map(BreakdownMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
