package niu.itmo.spaceresearch.controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author amifideles
 */
@Slf4j
@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("userInfo", new UserInfo());
        return "test";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String handleForm(Model model, @ModelAttribute("userInfo") UserInfo userInfo) {
        log.info("first Name : {}", userInfo.getFirstName());
        log.info("Last Name : {}", userInfo.getLastName());
        model.addAttribute("userInfo", new UserInfo());
        return "test";
    }
}
