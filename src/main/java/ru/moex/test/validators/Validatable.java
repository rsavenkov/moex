package ru.moex.test.validators;

import ru.moex.test.dto.ClientDTO;

import java.util.List;

/**
 * Validation interface
 */
public interface Validatable {

    /**
     * Perform fields validation
     *
     * @param dto
     * @return
     */
    boolean validate(ClientDTO dto);

    /**
     * Return required fields
     *
     * @return
     */
    List<String> getFields();
}
