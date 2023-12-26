package niu.itmo.spaceresearch.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.ReportDto;
import niu.itmo.spaceresearch.dto.ReportRequestDto;
import niu.itmo.spaceresearch.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author amifideles
 */
@SecurityRequirement(name = "basicAuth")
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@RestController
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> getReportById(@PathVariable Integer id) {
        ReportDto reportDto = reportService.getReportById(id);
        return ResponseEntity.ok(reportDto);
    }

    @PostMapping("")
    public ResponseEntity<?> createReport(@RequestBody ReportRequestDto reportDto) {
        reportService.createReport(reportDto);
        return ResponseEntity.noContent().build();
    }

}
