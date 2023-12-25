package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.CabinDto;
import niu.itmo.spaceresearch.model.Cabins;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author amifideles
 */
public class CabinsMapper {
    // TODO Нужен засетить нормально значение access
    public static CabinDto toCabinDto(Cabins cabins) {
        return CabinDto.builder()
                .id(cabins.getId())
                .name(cabins.getName())
                .currentHasAccess(false)
                .volumeCubicMeters(cabins.getVolumeCubicMeters())
                .limitedAccess(cabins.getLimitedAccess())
                .build();
    }

    public static List<CabinDto> toCabinDtoList(List<Cabins> cabinsList) {
        return cabinsList.stream()
                .map(CabinsMapper::toCabinDto)
                .collect(Collectors.toList());
    }
}
