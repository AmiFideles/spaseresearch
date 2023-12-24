package niu.itmo.spaceresearch.dto;

import lombok.Builder;
import niu.itmo.spaceresearch.model.Gender;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

@Builder
public record ResearcherDto(
        @Nullable
        Integer id,
        String firstName,
        String lastName,
        Integer age,
        Gender gender,
        @Nullable
        Boolean isCapitan,
        Set<ProfessionDto> professions
) {
}
