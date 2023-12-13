package niu.itmo.spaceresearch.dto;

import niu.itmo.spaceresearch.model.Gender;
import org.springframework.lang.Nullable;

import java.util.List;

public record ResearcherDto(
        @Nullable
        Integer id,
        String name,
        String surname,
        Integer age,
        Gender gender,
        @Nullable
        Boolean isCapitan,
        List<ProfessionDto> professions
) {
}
