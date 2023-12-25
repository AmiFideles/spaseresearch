package niu.itmo.spaceresearch.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.response.station.DetailedStationDto;
import niu.itmo.spaceresearch.service.StationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author amifideles
 */
@SecurityRequirement(name = "basicAuth")
@RequestMapping("/api/stations")
@RequiredArgsConstructor
@RestController
public class StationController {
    private final StationService stationService;

    @GetMapping("")
    public ResponseEntity<?> getAllStations() {
        return ResponseEntity.ok(stationService.getAllStations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedStationDto> getStationById(@PathVariable Integer id) {
        DetailedStationDto station = stationService.getStationById(id);
        return ResponseEntity.ok(station);
    }

}
