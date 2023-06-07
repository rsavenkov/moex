package ru.moex.test.validators;

import ru.moex.test.Client;

import java.util.List;

/**
 * Field validation interface
 */
public interface Validatable {

    /**
     * Perform fields validation
     *
     * @param client
     * @return
     */
    boolean validate(Client client);

    /**
     * Return required fields
     *
     * @return
     */
    List<String> getFields();
}
