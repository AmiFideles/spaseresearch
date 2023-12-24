package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.ProfessionDto;
import niu.itmo.spaceresearch.model.Profession;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author amifideles
 */
public class ProfessionMapper {

    public static ProfessionDto toDto(Profession profession) {
        return ProfessionDto.builder()
                .id(profession.getId())
                .name(profession.getName())
                .build();
    }

    public static Set<ProfessionDto> mapProfessionsToListDto(Set<Profession> professions) {
        return professions.stream()
                .map(ProfessionMapper::toDto)
                .collect(Collectors.toSet());
    }
}
