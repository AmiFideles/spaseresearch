package niu.itmo.spaceresearch.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.response.spaceship.DetailedSpaceshipDto;
import niu.itmo.spaceresearch.service.SpaceshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author amifideles
 */
@SecurityRequirement(name = "basicAuth")
@RequestMapping("/api/spaceships")
@RequiredArgsConstructor
@RestController
public class SpaceshipController {
    private final SpaceshipService spaceshipService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getSpaceshipById(@PathVariable Integer id) {
        DetailedSpaceshipDto spaceship = spaceshipService.getSpaceshipById(id);
        return ResponseEntity.ok(spaceship);
    }

    @GetMapping("/ships/{sourceStationId}/{destinationStationId}")
    public ResponseEntity<List<DetailedSpaceshipDto>> getAvailableShips(
            @PathVariable Integer sourceStationId,
            @PathVariable Integer destinationStationId
    ) {
        List<DetailedSpaceshipDto> availableShips = spaceshipService.getSuitableSpaceships(sourceStationId, destinationStationId);
        return ResponseEntity.ok(availableShips);
    }
}
