package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.BreakdownDto;
import niu.itmo.spaceresearch.model.BreakdownType;

/**
 * @author amifideles
 */
public class BreakdownMapper {
    public static BreakdownDto mapToDto(BreakdownType breakdownType) {
        return BreakdownDto.builder()
                .id(breakdownType.getId())
                .name(breakdownType.getName())
                .build();
    }
}
