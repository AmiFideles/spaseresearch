package niu.itmo.spaceresearch.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.ExpeditionResponseDto;
import niu.itmo.spaceresearch.dto.request.ExpeditionRequestDto;
import niu.itmo.spaceresearch.service.api.ExpeditionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        expeditionService.createExpeditionUsingFunction(expeditionRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<ExpeditionResponseDto>> getAllExpeditions() {
        List<ExpeditionResponseDto> expeditions = expeditionService.getAllExpeditions();
        return ResponseEntity.ok(expeditions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpeditionResponseDto> getExpeditionById(@PathVariable Integer id) {
        Optional<ExpeditionResponseDto> expedition = expeditionService.getExpeditionById(id);
        return expedition.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/researcher/{researcherId}")
    public ResponseEntity<List<ExpeditionResponseDto>> getResearcherExpeditions(@PathVariable Integer researcherId) {
        List<ExpeditionResponseDto> expeditions = expeditionService.getResearcherExpeditions(researcherId);
        return ResponseEntity.ok(expeditions);
    }
}
