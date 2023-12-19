package niu.itmo.spaceresearch.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import niu.itmo.spaceresearch.dto.*;
import niu.itmo.spaceresearch.service.api.ExpeditionService;
import niu.itmo.spaceresearch.service.api.ResearcherService;
import niu.itmo.spaceresearch.service.api.SpaceshipService;
import niu.itmo.spaceresearch.service.api.StationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private final ExpeditionService expeditionService;
    private final StationService stationService;
    private final SpaceshipService spaceshipService;
    private final ResearcherService researcherService;

    @GetMapping("/")
    public String getExpeditions(Model model) {
        List<ExpeditionDto> expeditions = expeditionService.getResearcherExpeditions(1);
        expeditions.sort(Comparator.comparing(ExpeditionDto::departureTime));
        model.addAttribute("expeditions", expeditions);
        return "expeditionHistory";
    }

    @GetMapping("/{id}")
    public String getExpeditions(Model model, @PathVariable Integer id) {
        List<ExpeditionDto> expeditions = expeditionService.getResearcherExpeditions(1);
        expeditions.sort(Comparator.comparing(ExpeditionDto::departureTime));
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
    public String postProcessChooseSourceAndDestinationStation(Model model, @ModelAttribute("stationId") StationIdDto stationIdDto) {
        Integer sourceStationId = stationIdDto.getSourceStationId();
        Integer destinationStationId = stationIdDto.getDestinationStationId();
        return "redirect:/expedition/createExpeditionSecondStage?sourceStationId=" + sourceStationId + "&destinationStationId=" + destinationStationId;
    }

    @GetMapping("/createExpeditionSecondStage")
    public String processChooseSourceAndDestinationStation(Model model, @RequestParam("sourceStationId") Integer sourceStationId, @RequestParam("destinationStationId") Integer destinationStationId) {
        StationDto sourceStation = stationService.getStationById(sourceStationId).orElseThrow();
        StationDto destinationStation = stationService.getStationById(destinationStationId).orElseThrow();
        model.addAttribute("sourceStation", sourceStation);
        model.addAttribute("destinationStation", destinationStation);
        List<SpaceshipDto> suitableSpaceships = spaceshipService.getSuitableSpaceships(sourceStationId, sourceStation.planet().planetType().id());
        model.addAttribute("suitableSpaceships", suitableSpaceships);
        model.addAttribute("chosenSpaceshipId", new SpaceshipIdDto());
        return "createExpeditionSecondStage";
    }

    @PostMapping("/createExpeditionThirdStage")
    public String postProcessChooseResearchers(Model model, @ModelAttribute("chosenSpaceshipId") SpaceshipIdDto spaceshipIdDto) {
        Integer sourceStationId = spaceshipIdDto.getSourceStationId();
        Integer destinationStationId = spaceshipIdDto.getDestinationStationId();
        Integer spaceshipId = spaceshipIdDto.getSpaceshipId();
        return "redirect:/expedition/createExpeditionThirdStage?sourceStationId=%s&destinationStationId=%s&spaceshipId=%s".formatted(sourceStationId, destinationStationId, spaceshipId);
    }

    @GetMapping("/createExpeditionThirdStage")
    public String processChooseResearchers(Model model, @RequestParam Integer sourceStationId, @RequestParam Integer destinationStationId, @RequestParam Integer spaceshipId) {
        StationDto sourceStation = stationService.getStationById(sourceStationId).orElseThrow();
        StationDto destinationStation = stationService.getStationById(destinationStationId).orElseThrow();
        model.addAttribute("sourceStation", sourceStation);
        model.addAttribute("destinationStation", destinationStation);

        SpaceshipDto spaceshipDto = spaceshipService.getSpaceshipById(spaceshipId).orElseThrow();
        model.addAttribute("spaceshipDto", spaceshipDto);

        List<ResearcherDto> availableResearchers = researcherService.getAvailableResearchers();
        model.addAttribute("availableResearchers", availableResearchers);
        return "createExpeditionThirdStage";
    }

    @PostMapping
    public String submitExpedition(@RequestBody ExpeditionSubmitDto expeditionSubmitDto) {
        Integer sourceStationId = expeditionSubmitDto.getSourceStationId();
        Integer destinationStationId = expeditionSubmitDto.getDestinationStationId();
        Integer spaceshipId = expeditionSubmitDto.getSpaceshipId();
        List<Integer> selectedResearchers = expeditionSubmitDto.getSelectedResearchers();
        Integer currentId = 1;
        expeditionService.createExpedition(sourceStationId, destinationStationId, spaceshipId, selectedResearchers, currentId);
        return "index";
    }

}
