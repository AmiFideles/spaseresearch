package niu.itmo.spaceresearch.service.api;

import niu.itmo.spaceresearch.dto.response.expedition.DetailedExpeditionDto;
import niu.itmo.spaceresearch.dto.request.ExpeditionRequestDto;

import java.util.List;
import java.util.Optional;

public interface ExpeditionService {
    void createExpeditionUsingFunction(
        ExpeditionRequestDto expeditionRequestDto
    );

    void  createExpedition(ExpeditionRequestDto expeditionRequestDto);

    List<DetailedExpeditionDto> getAllExpeditions();

    List<DetailedExpeditionDto> getResearcherExpeditions(Integer researcherId);

    Optional<DetailedExpeditionDto> getExpeditionById(Integer id);
}
