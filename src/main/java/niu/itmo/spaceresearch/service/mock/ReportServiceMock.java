package niu.itmo.spaceresearch.service.mock;

import lombok.extern.slf4j.Slf4j;
import niu.itmo.spaceresearch.dto.BreakdownDto;
import niu.itmo.spaceresearch.service.api.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReportServiceMock implements ReportService {
    public static BreakdownDto mockBreakdown(Integer id) {
        return new BreakdownDto(
                id,
                "Test breakdown %s".formatted(id)
        );
    }

    @Override
    public void createReport(List<Integer> breakdownsIds) {
        log.info("Created report: %s".formatted(breakdownsIds));
    }

    @Override
    public List<BreakdownDto> getAllBreakdowns() {
        return List.of(
                mockBreakdown(1),
                mockBreakdown(2),
                mockBreakdown(3)
        );
    }
}
