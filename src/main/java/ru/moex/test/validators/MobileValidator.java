package ru.moex.test.validators;

import ru.moex.test.dto.ClientDTO;

import java.util.Arrays;
import java.util.List;

/**
 * Mobile validator
 */
public class MobileValidator implements Validatable {

    private static final List<String> fields = Arrays.asList("phone");

    @Override
    public boolean validate(ClientDTO dto) {
        //TODO validate phone by regex
        return dto.getPhone() == null || dto.getPhone().trim() == "" ? false : true;
    }

    @Override
    public List<String> getFields() {
        return fields;
    }
}
