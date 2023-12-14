package niu.itmo.spaceresearch.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import niu.itmo.spaceresearch.model.Gender;

import java.util.List;

/**
 * @author amifideles
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private Integer age;
    @NotEmpty
    private Gender gender;
    @NotEmpty(message = "Email should not be empty")
    private String username;
    @NotEmpty(message = "Password should not be empty")
    private String password;
    @NotNull
    List<ProfessionDto> professions;
}
