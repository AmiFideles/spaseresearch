package niu.itmo.spaceresearch.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.BreakdownTypeDto;
import niu.itmo.spaceresearch.service.BreakdownTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author amifideles
 */
@SecurityRequirement(name = "basicAuth")
@RequestMapping("/api/breakdowns")
@RequiredArgsConstructor
@RestController
public class BreakdownTypeController {
    private final BreakdownTypeService breakdownTypeService;

    @GetMapping("")
    public ResponseEntity<?> getAllBreakdownTypes() {
        List<BreakdownTypeDto> all = breakdownTypeService.getAllBreakdownTypes();
        return ResponseEntity.ok(all);
    }
}
