package niu.itmo.spaceresearch.controller;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.service.api.SpaceshipService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author amifideles
 */
@RequiredArgsConstructor
@RestController
public class SpaceshipController {
    private final SpaceshipService spaceshipService;

}
