package niu.itmo.spaceresearch.dto.request;

import niu.itmo.spaceresearch.model.Gender;
import org.springframework.lang.Nullable;

import java.util.Set;

/**
 * @author amifideles
 */
public record ResearcherRequestDto(@Nullable
                                   String firstName,
                                   String lastName,
                                   String username,
                                   String password,
                                   Integer age,
                                   Gender gender,
                                   Set<Long> professions
) {
}
