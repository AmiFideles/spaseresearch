package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.BreakdownTypeDto;
import niu.itmo.spaceresearch.model.BreakdownType;

/**
 * @author amifideles
 */
public class BreakdownMapper {
    public static BreakdownTypeDto mapToDto(BreakdownType breakdownType) {
        return BreakdownTypeDto.builder()
                .id(breakdownType.getId())
                .name(breakdownType.getName())
                .build();
    }
}
