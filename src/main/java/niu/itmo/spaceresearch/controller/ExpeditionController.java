package niu.itmo.spaceresearch.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import niu.itmo.spaceresearch.dto.StationDto;
import niu.itmo.spaceresearch.dto.StationIdDto;
import niu.itmo.spaceresearch.model.Expedition;
import niu.itmo.spaceresearch.service.StationService;
import niu.itmo.spaceresearch.service.api.ExpeditionService;
import niu.itmo.spaceresearch.service.api.SpaceshipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author amifideles
 */
@RequestMapping("/expedition")
@Controller
@Slf4j
@RequiredArgsConstructor
public class ExpeditionController {
    private ExpeditionService expeditionService;
    private final StationService stationService;
    private final SpaceshipService spaceshipService;
    @GetMapping("/")
    public String getExpeditions(Model model) {
        String username = "username";
        List<Expedition> expeditions = expeditionService.getExpeditionsByUsername(username);
        expeditions.sort(Comparator.comparing(Expedition::getDepartureTime));
        model.addAttribute("expeditions", expeditions);
        return "expeditionHistory";
    }

    @GetMapping("/{id}")
    public String getExpeditions(Model model, @PathVariable Integer id) {
        String username = "username";
        List<Expedition> expeditions = expeditionService.getExpeditionsByUsername(username);
        expeditions.sort(Comparator.comparing(Expedition::getDepartureTime));
        model.addAttribute("expedition", expeditions.get(0));
        return "expeditionDescription";
    }

    @GetMapping("/createExpeditionFirstStage")
    public String chooseSourceAndDestinationStation(Model model) {
        List<StationDto> sourceStation = stationService.getAllStations();
        model.addAttribute("sourceStation", sourceStation);
        List<StationDto> destinationStation = stationService.getAllStations();
        model.addAttribute("destinationStation", destinationStation);
        model.addAttribute("stationId", new StationIdDto());
        return "createExpeditionFirstStage";
    }



    @GetMapping("/createExpeditionSecondStage")
    public String processChooseSourceAndDestinationStation(Model model, @RequestParam("sourceStationId") Integer sourceStationId, @RequestParam("destinationStationId") Integer destinationStationId) {
        // Запрос на то, чтобы  получить кораблей, которые находятся в sourceStation и которые имеют право полететь на
        Optional<StationDto> sourceStation = stationService.getStationById(sourceStationId);
        Optional<StationDto> destinationStation = stationService.getStationById(destinationStationId);
        model.addAttribute("sourceStation", sourceStation.get());
        model.addAttribute("destinationStation", destinationStation.get());
        return "createExpeditionSecondStage";
    }
}
