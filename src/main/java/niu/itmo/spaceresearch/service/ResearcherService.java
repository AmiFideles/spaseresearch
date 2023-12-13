package niu.itmo.spaceresearch.service;

import niu.itmo.spaceresearch.dto.ResearcherDto;

import java.util.List;

public interface ResearcherService {
    List<ResearcherDto> getAvailableResearchers();
}
