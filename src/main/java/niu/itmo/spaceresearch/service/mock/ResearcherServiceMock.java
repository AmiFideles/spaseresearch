package niu.itmo.spaceresearch.service.mock;

import niu.itmo.spaceresearch.dto.ProfessionDto;
import niu.itmo.spaceresearch.dto.ResearcherDto;
import niu.itmo.spaceresearch.model.Gender;
import niu.itmo.spaceresearch.service.api.ResearcherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResearcherServiceMock implements ResearcherService {
    public static ResearcherDto mockResearcher(Integer id, Boolean isCapitan) {
        return new ResearcherDto(
                id,
                "Test researcher #%s first name".formatted(id),
                "Test researcher #%s second name".formatted(id),
                42,
                Gender.MALE,
                isCapitan,
                List.of(
                        mockProfession(id),
                        mockProfession(id + 1),
                        mockProfession(id + 2)
                )
        );
    }

    public static ProfessionDto mockProfession(Integer id) {
        return new ProfessionDto(
                id,
                "Test profession #%s".formatted(id)
        );
    }

    @Override
    public List<ResearcherDto> getAvailableResearchers() {
        return List.of(
                mockResearcher(1, null),
                mockResearcher(2, null),
                mockResearcher(3, null)
        );
    }

    @Override
    public Optional<ResearcherDto> findByUsername(String username) {
        return Optional.of(mockResearcher(1, null));
    }
}
