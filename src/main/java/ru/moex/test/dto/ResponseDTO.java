package ru.moex.test.dto;

import ru.moex.test.Client;

import java.util.Arrays;
import java.util.List;

/**
 * Response dto
 */
public class ResponseDTO {

    /**
     * Logical status code - ok or error
     */
    private String status;

    /**
     * Message describes business logic error
     */
    private String message;

    /**
     * Result as Client object
     */
    private List<Client> clients;

    public ResponseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDTO(String status, String message, Client client) {
        this.status = status;
        this.message = message;
        this.clients = Arrays.asList(client);
    }

    public ResponseDTO(String status, String message, List<Client> clients) {
        this.status = status;
        this.message = message;
        this.clients = clients;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Client> getClients() {
        return clients;
    }
}
