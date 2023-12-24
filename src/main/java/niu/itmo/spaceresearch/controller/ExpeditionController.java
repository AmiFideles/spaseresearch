package niu.itmo.spaceresearch.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.request.ExpeditionRequestDto;
import niu.itmo.spaceresearch.dto.response.expedition.DetailedExpeditionDto;
import niu.itmo.spaceresearch.dto.response.expedition.SimpleExpeditionDto;
import niu.itmo.spaceresearch.service.impl.ExpeditionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * @author amifideles
 */
@SecurityRequirement(name = "basicAuth")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/expedition")
public class ExpeditionController {
    private final ExpeditionService expeditionService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("string");
    }

    @PostMapping()
    public ResponseEntity<?> createExpedition(ExpeditionRequestDto expeditionRequestDto) {
        expeditionService.createExpedition(expeditionRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<DetailedExpeditionDto> getExpeditionById(@PathVariable Integer id) {
        Optional<DetailedExpeditionDto> expedition = expeditionService.getExpeditionById(id);
        return expedition.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/list")
    public ResponseEntity<List<SimpleExpeditionDto>> getResearcherExpeditions(Principal principal) {
        List<SimpleExpeditionDto> expeditions = expeditionService.getResearcherExpeditions(principal);
        return ResponseEntity.ok(expeditions);
    }
}
