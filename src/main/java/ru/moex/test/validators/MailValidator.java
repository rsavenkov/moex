package ru.moex.test.validators;

import ru.moex.test.Client;

import java.util.Arrays;
import java.util.List;

/**
 * Mail field validator
 */
public class MailValidator implements Validatable {

    private static final List<String> fields = Arrays.asList("firstName", "email");

    @Override
    public boolean validate(Client client) {
        return (client.getFirstName() == null || client.getFirstName().trim().isEmpty())
                || (client.getEmail() == null || client.getEmail().trim().isEmpty() || !client.getEmail().matches("^(.+)@(\\\\S+)$")) ? false : true;
    }

    @Override
    public List<String> getFields() {
        return fields;
    }
}
