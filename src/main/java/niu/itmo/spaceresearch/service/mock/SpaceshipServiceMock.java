package niu.itmo.spaceresearch.service.mock;

import niu.itmo.spaceresearch.dto.ManufacturerDto;
import niu.itmo.spaceresearch.dto.SpaceshipDto;
import niu.itmo.spaceresearch.model.PlanetType;
import niu.itmo.spaceresearch.service.api.SpaceshipService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SpaceshipServiceMock implements SpaceshipService {
    public static SpaceshipDto mockSpaceship(Integer id) {
        return new SpaceshipDto(
                id,
                "Test spaceship #%s".formatted(id),
                LocalDate.now(),
                BigDecimal.ONE,
                42,
                mockManufacturer(id),
                List.of(),
                true,
                StationServiceMock.mockStation(id)
        );
    }

    private static ManufacturerDto mockManufacturer(Integer id) {
        return new ManufacturerDto(
                id,
                "Test manufacturer #%s".formatted(id),
                "Test country"
        );
    }

    @Override
    public Optional<SpaceshipDto> getSpaceshipById(Integer id) {
        return Optional.of(mockSpaceship(id));
    }

    @Override
    public List<SpaceshipDto> getSuitableSpaceship(Integer stationId, PlanetType planetType) {
        return List.of(
                mockSpaceship(1),
                mockSpaceship(2),
                mockSpaceship(3)
        );
    }
}
