package ru.moex.test.validators;

import ru.moex.test.dto.ClientDTO;

import java.util.Arrays;
import java.util.List;

/**
 * Mail validator
 */
public class MailValidator implements Validatable {

    private static final List<String> fields = Arrays.asList("firstName", "email");

    @Override
    public boolean validate(ClientDTO dto) {
        //TODO validate email by regex
        return (dto.getFirstName() == null || dto.getFirstName().trim() == "") || (dto.getEmail() == null || dto.getEmail().trim() == "") ? false : true;
    }

    @Override
    public List<String> getFields() {
        return fields;
    }
}
