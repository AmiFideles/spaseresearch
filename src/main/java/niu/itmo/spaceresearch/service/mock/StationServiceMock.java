package niu.itmo.spaceresearch.service.mock;

import niu.itmo.spaceresearch.dto.GalaxyDto;
import niu.itmo.spaceresearch.dto.PlanetDto;
import niu.itmo.spaceresearch.dto.PlanetTypeDto;
import niu.itmo.spaceresearch.dto.StationDto;
import niu.itmo.spaceresearch.service.api.StationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StationServiceMock implements StationService {
    public static StationDto mockStation(Integer id) {
        return new StationDto(
                id,
                "Test station #%s".formatted(id),
                LocalDate.now(),
                BigDecimal.ONE,
                BigDecimal.ONE,
                mockPlanet(id)
        );
    }

    public static PlanetDto mockPlanet(Integer id) {
        return new PlanetDto(
                id,
                "Test planet #%s".formatted(id),
                BigDecimal.ONE,
                mockGalaxy(id),
                mockPlanetType(id)
        );
    }

    private static GalaxyDto mockGalaxy(Integer id) {
        return new GalaxyDto(
                id,
                "Test galaxy #%s".formatted(id),
                LocalDateTime.now(),
                BigDecimal.ONE
        );
    }

    private static PlanetTypeDto mockPlanetType(Integer id) {
        return new PlanetTypeDto(
                id,
                "Test planet type #%s".formatted(id)
        );
    }

    @Override
    public List<StationDto> getAllStations() {
        return List.of(
                mockStation(1),
                mockStation(2),
                mockStation(3)
        );
    }

    @Override
    public Optional<StationDto> getStationById(Integer id) {
        return Optional.of(mockStation(id));
    }
}
