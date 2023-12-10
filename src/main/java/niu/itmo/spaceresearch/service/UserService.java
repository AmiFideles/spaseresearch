package niu.itmo.spaceresearch.service;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.UserDto;
import niu.itmo.spaceresearch.model.Profession;
import niu.itmo.spaceresearch.model.Researcher;
import niu.itmo.spaceresearch.repository.RoleRepository;
import niu.itmo.spaceresearch.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author amifideles
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(UserDto userDto) {
        Researcher researcher = new Researcher();
        researcher.setName(userDto.getFirstName() + " " + userDto.getLastName());
        researcher.setEmail(userDto.getEmail());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        researcher.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Profession profession = roleRepository.findByName("ROLE_ADMIN");
        if (profession == null) {
            profession = checkRoleExist();
        }
        researcher.setProfessions(Arrays.asList(profession));
        userRepository.save(researcher);
    }

    public Researcher findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserDto> findAllUsers() {
        List<Researcher> researchers = userRepository.findAll();
        return researchers.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(Researcher researcher) {
        UserDto userDto = new UserDto();
        String[] name = researcher.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(researcher.getEmail());
        return userDto;
    }

    private Profession checkRoleExist() {
        Profession profession = new Profession();
        profession.setName("ROLE_ADMIN");
        return roleRepository.save(profession);
    }
}
