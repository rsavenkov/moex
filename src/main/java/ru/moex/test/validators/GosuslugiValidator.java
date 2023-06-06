package ru.moex.test.validators;

import ru.moex.test.dto.ClientDTO;

import java.util.Arrays;
import java.util.List;

/**
 * Gosuslugi validator
 */
public class GosuslugiValidator implements Validatable {

    private static final List<String> fields = Arrays.asList("bankId", "birthDate", "lastName", "firstName", "thirdName", "passport", "regAddress", "birthPlace", "phone");

    @Override
    public boolean validate(ClientDTO dto) {
        //TODO validate phone and passport by regex
        return dto.getBankId() == null || dto.getBirthDate() == null ||
                (dto.getLastName() == null || dto.getLastName().trim() == "") ||
                (dto.getFirstName() == null || dto.getFirstName().trim() == "") ||
                (dto.getThirdName() == null || dto.getThirdName().trim() == "") ||
                (dto.getPassport() == null || dto.getPassport().trim() == "") ||
                (dto.getRegAddress() == null || dto.getRegAddress().trim() == "") ||
                (dto.getBirthPlace() == null || dto.getBirthPlace().trim() == "") ||
                (dto.getPhone() == null || dto.getPhone().trim() == "") ? false : true;
    }

    @Override
    public List<String> getFields() {
        return fields;
    }
}
