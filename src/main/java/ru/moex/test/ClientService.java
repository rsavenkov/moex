package ru.moex.test;

import java.util.List;
import java.util.Optional;

/**
 * Client service
 */
public interface ClientService {

    /**
     * Get client by id
     *
     * @param id
     * @return
     */
    Optional<Client> getClientById(long id);

    /**
     * Save client
     *
     * @param client
     * @return
     */
    Client saveClient(Client client);

    /**
     * Find clients by specified fields
     *
     * @param lastName
     * @param firstName
     * @param thirdName
     * @param phone
     * @param email
     * @return
     */
    List<Client> getClients(String lastName, String firstName, String thirdName, String phone, String email);
}
