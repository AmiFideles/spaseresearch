package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.BreakdownTypeDto;
import niu.itmo.spaceresearch.model.BreakdownType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author amifideles
 */
public class BreakdownTypeMapper {
    public static BreakdownTypeDto toBreakdownTypeDto(BreakdownType breakdownType) {
        return BreakdownTypeDto.builder()
                .id(breakdownType.getId())
                .name(breakdownType.getName())
                .build();
    }

    public static List<BreakdownTypeDto> toListBreakdownTypeDto(List<BreakdownType> all) {
        return all.stream().map(BreakdownTypeMapper::toBreakdownTypeDto).collect(Collectors.toList());
    }
}
