package ru.moex.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.moex.test.exceptions.FieldValidationException;
import ru.moex.test.exceptions.InvalidHeaderException;
import ru.moex.test.exceptions.ParamsNotPassedException;
import ru.moex.test.validators.Validators;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Client controller
 */
@RestController()
@RequestMapping("/client")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> get(@PathVariable("id") long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("find")
    public List<Client> find(@RequestParam(required = false) String lastName,
                             @RequestParam(required = false) String firstName,
                             @RequestParam(required = false) String thirdName,
                             @RequestParam(required = false) String phone,
                             @RequestParam(required = false) String email) {
        if (lastName == null && firstName == null && thirdName == null && phone == null && email == null) {
            throw new ParamsNotPassedException();
        }
        return clientService.getClients(lastName, firstName, thirdName, phone, email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client, HttpServletRequest request) {
        String header = request.getHeader("x-Source");
        Validators validator = Validators.getByName(header);
        if (validator == null) {
            logger.error(String.format("No suitable validator found for x-Source = %s", header));
            throw new InvalidHeaderException(String.format("Не найден подходящий валидатор полей для заголовка x-Source = %s", header));
        }
        boolean isValid = validator.getValidator().validate(client);
        if (!isValid) {
            throw new FieldValidationException(String.format("Не переданы все обязательные поля %s для валидации, либо валидация прошла неуспешно!", String.join(", ", validator.getValidator().getFields())));
        }
        return clientService.saveClient(client);
    }
}
