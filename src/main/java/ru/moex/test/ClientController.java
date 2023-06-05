package ru.moex.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.moex.test.validators.Validator;

import javax.servlet.http.HttpServletRequest;

/**
 * Client controller
 */
@RestController("client")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @GetMapping("get")
    public String get(Long id) {

        return "get";
    }

    @GetMapping("find")
    public String find() {
        return "find";
    }

    @PostMapping(path = "save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody Client client, HttpServletRequest request) {
        String header = request.getHeader("x-Source");
//        Validator.values().
        return "create";
    }
}
