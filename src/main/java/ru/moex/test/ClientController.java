package ru.moex.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.moex.test.dto.ClientDTO;
import ru.moex.test.dto.ResponseDTO;
import ru.moex.test.validators.Validators;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Client controller
 */
@RestController()
@RequestMapping("client")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping(path = "/get/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> get(@PathVariable long id) {
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
//        if (client == null) {
//            logger.warn("Can't find client with id = " + id);
//            return new ResponseDTO("ok", String.format("Клиент с идентификатором = %s не найден", id));
//        }
//        return new ResponseDTO("ok", null, client);
    }

    @GetMapping(path = "/find",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseDTO find(@RequestParam String lastName, @RequestParam String firstName, @RequestParam String thirdName,
                                          @RequestParam String phone, @RequestParam String email) {
        /*if (lastName != null && lastName.trim() != "") {
            List<Client> clients = clientRepository.findByLastName(lastName);
            return clients.isEmpty() ? new ResponseDTO("ok", "По заданным параметрам ничего не найдено.", null) : new ResponseDTO("ok", "", clients.get(0));
        }
        if (firstName != null && firstName.trim() != "") {
            List<Client> clients = clientRepository.findByFirstName(firstName);
            return clients.isEmpty() ? new ResponseDTO("ok", "По заданным параметрам ничего не найдено.", null) : new ResponseDTO("ok", "", clients.get(0));
        }
        if (thirdName != null && thirdName.trim() != "") {
            List<Client> clients = clientRepository.findByThirdName(thirdName);
            return clients.isEmpty() ? new ResponseDTO("ok", "По заданным параметрам ничего не найдено.", null) : new ResponseDTO("ok", "", clients.get(0));
        }
        if (phone != null && phone.trim() != "") {
            List<Client> clients = clientRepository.findByPhone(phone);
            return clients.isEmpty() ? new ResponseDTO("ok", "По заданным параметрам ничего не найдено.", null) : new ResponseDTO("ok", "", clients.get(0));
        }
        if (email != null && email.trim() != "") {
            List<Client> clients = clientRepository.findByEmail(email);
            return clients.isEmpty() ? new ResponseDTO("ok", "По заданным параметрам ничего не найдено.", null) : new ResponseDTO("ok", "", clients.get(0));
        }*/
        List<Client> clients = clientRepository.findByLastNameOrFirstNameOrThirdNameOrPhoneOrEmail(lastName, firstName, thirdName, phone, email);
        logger.warn("No one of params was passed to find method");
        return new ResponseDTO("ok", "Ни один из параметров не передан!", clients);
    }

    @PostMapping(path = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseDTO create(@RequestBody ClientDTO dto, HttpServletRequest request) {
        String header = request.getHeader("x-Source");
        Validators validator = Validators.getByName(header);
        if (validator == null) {
            logger.warn(String.format("No suitable validator found for x-Source = %s", header));
            return new ResponseDTO("error", String.format("Не найден подходящий валидатор полей для заголовка x-Source = %s", header));
        }
        boolean result = validator.getValidator().validate(dto);
        if (!result) {
            return new ResponseDTO("error", String.format("Не переданы все обязательные поля %s для валидации, либо валидация прошла неуспешно!", String.join(", ", validator.getValidator().getFields())));
        }
        try {
            Client client = clientRepository.save(new Client(dto));
            if (client == null) {
                logger.error("Some unexpected error is occurred during save operation.");
                return null;
            }
            return new ResponseDTO("ok", "", client);
        } catch (Exception e) {

        }
        return null;
    }
}
