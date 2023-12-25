package niu.itmo.spaceresearch.service;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.BreakdownTypeDto;
import niu.itmo.spaceresearch.dto.ReportDto;
import niu.itmo.spaceresearch.dto.ReportRequestDto;
import niu.itmo.spaceresearch.mapper.ReportMapper;
import niu.itmo.spaceresearch.model.BreakdownType;
import niu.itmo.spaceresearch.model.Expedition;
import niu.itmo.spaceresearch.model.Report;
import niu.itmo.spaceresearch.repository.BreakdownTypeRepository;
import niu.itmo.spaceresearch.repository.ExpeditionRepository;
import niu.itmo.spaceresearch.repository.ReportRepository;
import niu.itmo.spaceresearch.service.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author amifideles
 */
@RequiredArgsConstructor
@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final ExpeditionRepository expeditionRepository;
    private final BreakdownTypeRepository breakdownTypeRepository;

    public ReportDto getReportById(Integer id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ReportDescription not found with id: " + id));

        return ReportMapper.mapToDto(report);
    }

    public void createReport(ReportRequestDto reportRequestDto) {
        Report report = new Report();
        Expedition expedition = expeditionRepository.findById(reportRequestDto.expeditionId())
                .orElseThrow(() -> new EntityNotFoundException("Expedition not found with id: " + reportRequestDto.expeditionId()));
        report.setExpedition(expedition);

        // Получаем типы поломок по id и добавляем их в отчет
        Set<BreakdownType> breakdownTypes = new HashSet<>();
        for (BreakdownTypeDto breakdownTypeDto : reportRequestDto.breakdowns()) {
            BreakdownType breakdownType = breakdownTypeRepository.findById(breakdownTypeDto.id())
                    .orElseThrow(() -> new EntityNotFoundException("BreakdownType not found with id: " + breakdownTypeDto.id()));
            breakdownTypes.add(breakdownType);
        }
        report.setBreakdownTypes(breakdownTypes);

        reportRepository.save(report);
    }
}
