package niu.itmo.spaceresearch.controller;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.ProfessionDto;
import niu.itmo.spaceresearch.service.ProfessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author amifideles
 */
@RequestMapping("/api/professions")
@RequiredArgsConstructor
@RestController
public class ProfessionController {
    private final ProfessionService professionService;

    @GetMapping("")
    public ResponseEntity<List<ProfessionDto>> getAllProfessions() {
        return ResponseEntity.ok(professionService.getAllProfessions());
    }


}
