package ru.moex.test.validators;

import ru.moex.test.Client;

import java.util.Arrays;
import java.util.List;

/**
 * Mobile field validator
 */
public class MobileValidator implements Validatable {

    private static final List<String> fields = Arrays.asList("phone");

    @Override
    public boolean validate(Client client) {
        return client.getPhone() == null || client.getPhone().trim().isEmpty() || !client.getPhone().matches("^7\\d{10}$") ? false : true;
    }

    @Override
    public List<String> getFields() {
        return fields;
    }
}
