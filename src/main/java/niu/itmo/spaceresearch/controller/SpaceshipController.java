package niu.itmo.spaceresearch.controller;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.model.Spaceship;
import niu.itmo.spaceresearch.service.api.SpaceshipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author amifideles
 */
@RequiredArgsConstructor
@RequestMapping("/spaceship")
@Controller
public class SpaceshipController {
    private final SpaceshipService spaceshipService;

    @GetMapping("/{id}")
    public String getExpeditions(Model model, @PathVariable Integer id) {
        Spaceship spaceship = spaceshipService.getSpaceshipById(id);
        model.addAttribute("spaceship", spaceship);
        return "spaceship";
    }

}
