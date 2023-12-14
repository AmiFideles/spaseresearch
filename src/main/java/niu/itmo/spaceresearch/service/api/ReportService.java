package niu.itmo.spaceresearch.service.api;

import niu.itmo.spaceresearch.dto.BreakdownDto;

import java.util.List;

public interface ReportService {
    void createReport(List<Integer> breakdownsIds);

    List<BreakdownDto> getAllBreakdowns();
}
