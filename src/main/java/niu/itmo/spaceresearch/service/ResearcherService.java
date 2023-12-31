package niu.itmo.spaceresearch.service;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.ResearcherDto;
import niu.itmo.spaceresearch.dto.request.AuthRequest;
import niu.itmo.spaceresearch.dto.request.ResearcherRequestDto;
import niu.itmo.spaceresearch.dto.response.LoginResponseDto;
import niu.itmo.spaceresearch.mapper.ResearcherMapper;
import niu.itmo.spaceresearch.model.Profession;
import niu.itmo.spaceresearch.model.Researcher;
import niu.itmo.spaceresearch.repository.ProfessionRepository;
import niu.itmo.spaceresearch.repository.ResearcherRepository;
import niu.itmo.spaceresearch.service.exceptions.ResearcherNotFound;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

/**
 * @author amifideles
 */
@Primary
@Service
@RequiredArgsConstructor
public class ResearcherService {
    private final PasswordEncoder passwordEncoder;
    private final ResearcherRepository researcherRepository;
    private final ProfessionRepository professionRepository;
    private final AuthenticationManager authenticationManager;

    public List<ResearcherDto> getAvailableResearchers(Principal principal) {
        Researcher captain = researcherRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResearcherNotFound(
                        "Researcher not found with username: %s"
                                .formatted(principal.getName())));
        List<Researcher> freeResearchers = researcherRepository.findFreeResearchers();
        freeResearchers.remove(captain);
        return ResearcherMapper.toListResearcherDto(freeResearchers);
    }

    public Optional<ResearcherDto> findByUsername(String username) {
        return Optional.empty();
    }

    public void createResearcher(ResearcherRequestDto requestDto) {
        Researcher researcher = ResearcherMapper.toEntity(requestDto);
        researcher.setPassword(passwordEncoder.encode(researcher.getPassword()));
        List<Profession> professions = professionRepository.findAllById(requestDto.professions());
        researcher.setProfessions(new HashSet<>(professions));
        researcherRepository.save(researcher);
    }

    public LoginResponseDto login(AuthRequest authRequest) {
        // аутентификации
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
        );
        String credentials = authRequest.username() + ":" + authRequest.password();
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        Researcher researcher = researcherRepository.findByUsername(authRequest.username())
                .orElseThrow(() -> new ResearcherNotFound(
                        "Researcher not found with username: %s"
                                .formatted(authRequest.username())));
        Set<Profession> professions = researcher.getProfessions();
        boolean hasCommanderProfession = professions.stream()
                .anyMatch(profession -> profession.getName().equals("Captain"));
        return LoginResponseDto.builder()
                .credentials(base64Credentials)
                .isCapitan(hasCommanderProfession)
                .build();
    }
}
