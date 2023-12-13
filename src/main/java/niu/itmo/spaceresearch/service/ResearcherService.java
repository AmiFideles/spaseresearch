package niu.itmo.spaceresearch.service;

import niu.itmo.spaceresearch.dto.ResearcherDto;

import java.util.List;
import java.util.Optional;

public interface ResearcherService {
    List<ResearcherDto> getAvailableResearchers();

    Optional<ResearcherDto> findByUsername(String username);
}
