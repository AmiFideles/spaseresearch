package niu.itmo.spaceresearch.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.response.spaceship.DetailedSpaceshipDto;
import niu.itmo.spaceresearch.service.SpaceshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<DetailedSpaceshipDto> getSpaceshipById(@PathVariable Integer id) {
        DetailedSpaceshipDto spaceship = spaceshipService.getSpaceshipById(id);
        return ResponseEntity.ok(spaceship);
    }

    @GetMapping("")
    public ResponseEntity<List<DetailedSpaceshipDto>> getAvailableShips(
            @RequestParam Integer sourceStationId,
            @RequestParam Integer destinationStationId
    ) {
        List<DetailedSpaceshipDto> availableShips = spaceshipService.getSuitableSpaceships(sourceStationId, destinationStationId);
        return ResponseEntity.ok(availableShips);
    }
}
