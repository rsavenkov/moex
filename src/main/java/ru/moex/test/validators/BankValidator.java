package ru.moex.test.validators;

import ru.moex.test.Client;

import java.util.Arrays;
import java.util.List;

/**
 * Bank field validator
 */
public class BankValidator implements Validatable {

    private static final List<String> fields = Arrays.asList("bankId", "birthDate", "lastName", "firstName", "thirdName", "passport");

    @Override
    public boolean validate(Client client) {
        return client.getBankId() == null || client.getBirthDate() == null ||
                (client.getLastName() == null || client.getLastName().trim().isEmpty()) ||
                (client.getFirstName() == null || client.getFirstName().trim().isEmpty()) ||
                (client.getThirdName() == null || client.getThirdName().trim().isEmpty()) ||
                (client.getPassport() == null || client.getPassport().trim().isEmpty() || client.getPassport().matches("^\\d{4} \\d{6}$")) ? false : true;
    }

    @Override
    public List<String> getFields() {
        return fields;
    }
}
