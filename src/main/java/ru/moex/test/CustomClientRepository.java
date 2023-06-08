package ru.moex.test;

import java.util.List;

/**
 * Custom client repository
 */
public interface CustomClientRepository  {

    /**
     * Find all client by specified fields with case sensitive
     *
     * @param lastName
     * @param firstName
     * @param thirdName
     * @param phone
     * @param email
     * @return
     */
    List<Client> findAllByFields(String lastName, String firstName, String thirdName, String phone, String email);
}