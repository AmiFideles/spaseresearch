package niu.itmo.spaceresearch.controller;

import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.request.AuthRequest;
import niu.itmo.spaceresearch.dto.request.ResearcherRequestDto;
import niu.itmo.spaceresearch.dto.response.LoginResponseDto;
import niu.itmo.spaceresearch.service.api.ResearcherService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

/**
 * @author amifideles
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final ResearcherService researcherService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("register")
    public ResponseEntity<?> registrationUser(@RequestBody ResearcherRequestDto researcherDto) {
        researcherService.createResearcher(researcherDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody AuthRequest authRequest) {
        String credentials = researcherService.login(authRequest);
        return ResponseEntity.ok(new LoginResponseDto(credentials));
    }

    @GetMapping("test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("test");
    }
}
