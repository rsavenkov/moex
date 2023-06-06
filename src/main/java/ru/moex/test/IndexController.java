package ru.moex.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.moex.test.validators.Validators;

/**
 * Index controller
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("headers", Validators.values());
        return "index";
    }

}
