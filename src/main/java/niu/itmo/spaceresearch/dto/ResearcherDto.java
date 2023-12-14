package niu.itmo.spaceresearch.dto;

import niu.itmo.spaceresearch.model.Gender;
import org.springframework.lang.Nullable;

import java.util.List;

public record ResearcherDto(
        @Nullable
        Integer id,
        String firstName,
        String lastName,
        Integer age,
        Gender gender,
        @Nullable
        Boolean isCapitan,
        List<ProfessionDto> professions
) {
}
