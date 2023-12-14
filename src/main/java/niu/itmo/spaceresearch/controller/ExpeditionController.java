package niu.itmo.spaceresearch.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import niu.itmo.spaceresearch.dto.StationDto;
import niu.itmo.spaceresearch.dto.StationIdDto;
import niu.itmo.spaceresearch.model.Expedition;
import niu.itmo.spaceresearch.service.StationService;
import niu.itmo.spaceresearch.service.api.ExpeditionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

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

    @PostMapping("/createExpeditionSecondStage")
    public String processChooseSourceAndDestinationStation() {
        log.info("test");
        return "redirect:/expedition/createExpeditionSecondStage?test=1";
    }

    @GetMapping("/createExpeditionSecondStage")
    public String chooseSpaceship(Model model) {
        return "index";
    }
}
