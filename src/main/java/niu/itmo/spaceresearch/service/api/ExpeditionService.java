package niu.itmo.spaceresearch.service.api;

import niu.itmo.spaceresearch.dto.ExpeditionResponseDto;
import niu.itmo.spaceresearch.dto.request.ExpeditionRequestDto;

import java.util.List;
import java.util.Optional;

public interface ExpeditionService {
    void createExpeditionUsingFunction(
        ExpeditionRequestDto expeditionRequestDto
    );

    void  createExpedition(ExpeditionRequestDto expeditionRequestDto);

    List<ExpeditionResponseDto> getAllExpeditions();

    List<ExpeditionResponseDto> getResearcherExpeditions(Integer researcherId);

    Optional<ExpeditionResponseDto> getExpeditionById(Integer id);
}
