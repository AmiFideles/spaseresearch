package niu.itmo.spaceresearch.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.request.ExpeditionRequestDto;
import niu.itmo.spaceresearch.dto.response.expedition.DetailedExpeditionDto;
import niu.itmo.spaceresearch.dto.response.expedition.SimpleExpeditionDto;
import niu.itmo.spaceresearch.service.ExpeditionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author amifideles
 */
@SecurityRequirement(name = "basicAuth")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/expeditions")
public class ExpeditionController {
    private final ExpeditionService expeditionService;

    @PostMapping()
    public ResponseEntity<?> createExpedition(Principal principal, @RequestBody ExpeditionRequestDto expeditionRequestDto) {
        expeditionService.createExpeditionUsingFunction(expeditionRequestDto, principal);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<SimpleExpeditionDto>> getResearcherExpeditions(Principal principal) {
        List<SimpleExpeditionDto> expeditions = expeditionService.getResearcherExpeditions(principal);
        return ResponseEntity.ok(expeditions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedExpeditionDto> getExpeditionById(@PathVariable Integer id, Principal principal) {
        DetailedExpeditionDto expedition = expeditionService.getExpeditionById(id, principal);
        return ResponseEntity.ok(expedition);
    }

    @PostMapping("/{expeditionId}")
    public ResponseEntity<?> completeExpedition(@PathVariable int expeditionId) {
        expeditionService.completeExpedition(expeditionId);
        return ResponseEntity.noContent().build();
    }

    // TODO: remove
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("string");
    }


}
