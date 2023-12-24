package niu.itmo.spaceresearch.mapper;

import niu.itmo.spaceresearch.dto.ProfessionDto;
import niu.itmo.spaceresearch.dto.ResearcherDto;
import niu.itmo.spaceresearch.dto.request.ResearcherRequestDto;
import niu.itmo.spaceresearch.model.Profession;
import niu.itmo.spaceresearch.model.Researcher;

import java.util.Set;
import java.util.stream.Collectors;

import static niu.itmo.spaceresearch.mapper.ProfessionMapper.mapProfessionsToListDto;

/**
 * @author amifideles
 */
public class ResearcherMapper {
    public static Researcher toEntity(ResearcherRequestDto researcherDto) {
        return Researcher.builder()
                .firstName(researcherDto.firstName())
                .lastName(researcherDto.lastName())
                .age(researcherDto.age())
                .gender(researcherDto.gender())
                .inExpedition(false)
                .username(researcherDto.username())
                .password(researcherDto.password())
                .build();
    }

    // TODO надо в сервис слое достать значение командира
    public static ResearcherDto toDto(Researcher researcher) {
        return ResearcherDto.builder()
                .id(researcher.getId())
                .firstName(researcher.getFirstName())
                .lastName(researcher.getLastName())
                .age(researcher.getAge())
                .gender(researcher.getGender())
                .professions(mapProfessionsToListDto(researcher.getProfessions()))
                .build();

    }


}
