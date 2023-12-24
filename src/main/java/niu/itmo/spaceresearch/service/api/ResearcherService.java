package niu.itmo.spaceresearch.service.api;

import niu.itmo.spaceresearch.dto.ResearcherDto;
import niu.itmo.spaceresearch.dto.request.AuthRequest;
import niu.itmo.spaceresearch.dto.request.ResearcherRequestDto;

import java.util.List;
import java.util.Optional;

public interface ResearcherService {
    List<ResearcherDto> getAvailableResearchers();

    void createResearcher(ResearcherRequestDto researcherDto);

    Optional<ResearcherDto> findByUsername(String username);

    String login(AuthRequest authRequest);
}
