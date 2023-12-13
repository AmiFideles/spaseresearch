package niu.itmo.spaceresearch.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import niu.itmo.spaceresearch.dto.UserDto;
import niu.itmo.spaceresearch.model.Researcher;
import niu.itmo.spaceresearch.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @author amifideles
 */
@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model) {
        Optional<Researcher> existing = userService.findByUsername(user.getUsername());
        if (existing.isPresent()) {
            result.rejectValue("email", null, "There is already an account registered with that email");
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }
}
