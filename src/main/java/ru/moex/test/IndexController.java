package ru.moex.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Index controller
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
