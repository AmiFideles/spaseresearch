package niu.itmo.spaceresearch.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.ResearcherDto;
import niu.itmo.spaceresearch.service.api.ResearcherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author amifideles
 */
@SecurityRequirement(name = "basicAuth")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/researchers")
public class ResearcherController {
    private final ResearcherService researcherService;

    @GetMapping("/free")
    public ResponseEntity<?> getAvailableResearchers() {
        List<ResearcherDto> availableResearchers = researcherService.getAvailableResearchers();
        return ResponseEntity.ok(availableResearchers);
    }
}
