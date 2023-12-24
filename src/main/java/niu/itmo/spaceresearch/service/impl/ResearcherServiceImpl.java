package niu.itmo.spaceresearch.service.impl;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.ResearcherDto;
import niu.itmo.spaceresearch.dto.request.AuthRequest;
import niu.itmo.spaceresearch.dto.request.ResearcherRequestDto;
import niu.itmo.spaceresearch.mapper.ResearcherMapper;
import niu.itmo.spaceresearch.model.Profession;
import niu.itmo.spaceresearch.model.Researcher;
import niu.itmo.spaceresearch.repository.ProfessionRepository;
import niu.itmo.spaceresearch.repository.ResearcherRepository;
import niu.itmo.spaceresearch.service.api.ResearcherService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * @author amifideles
 */
@Primary
@Service
@RequiredArgsConstructor
public class ResearcherServiceImpl implements ResearcherService {
    private final PasswordEncoder passwordEncoder;
    private final ResearcherRepository researcherRepository;
    private final ProfessionRepository professionRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public List<ResearcherDto> getAvailableResearchers() {
        return null;
    }

    @Override
    public Optional<ResearcherDto> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public void createResearcher(ResearcherRequestDto requestDto) {
        Researcher researcher = ResearcherMapper.toEntity(requestDto);
        researcher.setPassword(passwordEncoder.encode(researcher.getPassword()));
        List<Profession> professions = professionRepository.findAllById(requestDto.professions());
        researcher.setProfessions(new HashSet<>(professions));
        researcherRepository.save(researcher);
    }

    @Override
    public String login(AuthRequest authRequest) {
        // аутентификации
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
        );
        String credentials = authRequest.username() + ":" + authRequest.password();
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        return base64Credentials;
    }
}
