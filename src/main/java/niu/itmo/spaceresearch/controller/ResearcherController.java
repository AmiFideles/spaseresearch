package niu.itmo.spaceresearch.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.ResearcherDto;
import niu.itmo.spaceresearch.service.ResearcherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
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
    public ResponseEntity<?> getAvailableResearchers(Principal principal) {

        List<ResearcherDto> availableResearchers = researcherService.getAvailableResearchers(principal);
        return ResponseEntity.ok(availableResearchers);
    }
}
