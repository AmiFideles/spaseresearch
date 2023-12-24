package niu.itmo.spaceresearch.controller;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.service.impl.SpaceshipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author amifideles
 */
@RequestMapping("/api/spaceship")
@RequiredArgsConstructor
@RestController
public class SpaceshipController {
    private final SpaceshipService spaceshipService;

    @GetMapping("{id}")
    public void getSpaceshipById(@PathVariable Integer id) {
        spaceshipService.getSpaceshipById(id);
    }
}
