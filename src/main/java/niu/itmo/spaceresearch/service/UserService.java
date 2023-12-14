package niu.itmo.spaceresearch.service;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.UserDto;
import niu.itmo.spaceresearch.model.Gender;
import niu.itmo.spaceresearch.model.Researcher;
import niu.itmo.spaceresearch.repository.ProfessionRepository;
import niu.itmo.spaceresearch.repository.ResearcherRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * @author amifideles
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final ResearcherRepository researcherRepository;
    private final ProfessionRepository professionRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(UserDto user) {
        Researcher researcher = new Researcher();
        researcher.setFirstName(user.getFirstName());
        researcher.setLastName(user.getLastName());
        researcher.setPassword(user.getPassword());
        researcher.setUsername(user.getUsername());
        researcher.setInExpedition(false);
        // TODO: Fill all fields (professions if such exist)
        researcher.setAge(20);
        researcher.setGender(Gender.MALE);
        researcher.setProfessions(Set.of(professionRepository.findById(1L).orElseThrow()));
        //
        researcher.setPassword(passwordEncoder.encode(user.getPassword()));
        researcherRepository.save(researcher);
    }

    public Optional<Researcher> findByUsername(String username) {
        return researcherRepository.findByUsername(username);
    }
}
