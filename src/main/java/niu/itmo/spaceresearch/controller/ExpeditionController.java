package niu.itmo.spaceresearch.controller;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.model.Expedition;
import niu.itmo.spaceresearch.service.api.ExpeditionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;

/**
 * @author amifideles
 */
@Controller
@RequiredArgsConstructor
public class ExpeditionController {
    private ExpeditionService expeditionService;

    @GetMapping("/expeditions")
    public String getExpeditions(Model model) {
        String username = "username";
        List<Expedition> expeditions = expeditionService.getExpeditionsByUsername(username);
        expeditions.sort(Comparator.comparing(Expedition::getDepartureTime));
        model.addAttribute("expeditions", expeditions);
        return "expeditionHistory";
    }

    @GetMapping("/expeditions/{id}")
    public String getExpeditions(Model model, @PathVariable Integer id) {
        String username = "username";
        List<Expedition> expeditions = expeditionService.getExpeditionsByUsername(username);
        expeditions.sort(Comparator.comparing(Expedition::getDepartureTime));
        model.addAttribute("expedition", expeditions.get(0));
        return "expeditionDescription";
    }


}
