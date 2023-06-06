package ru.moex.test.validators;

import ru.moex.test.dto.ClientDTO;

import java.util.Arrays;
import java.util.List;

/**
 * Bank validator
 */
public class BankValidator implements Validatable {

    private static final List<String> fields = Arrays.asList("bankId", "birthDate", "lastName", "firstName", "thirdName", "passport");

    @Override
    public boolean validate(ClientDTO dto) {
        //TODO validate passport by regex
        return dto.getBankId() == null || dto.getBirthDate() == null ||
                (dto.getLastName() == null || dto.getLastName().trim() == "") ||
                (dto.getFirstName() == null || dto.getFirstName().trim() == "") ||
                (dto.getThirdName() == null || dto.getThirdName().trim() == "") ||
                (dto.getPassport() == null || dto.getPassport().trim() == "") ? false : true;
    }

    @Override
    public List<String> getFields() {
        return fields;
    }
}
