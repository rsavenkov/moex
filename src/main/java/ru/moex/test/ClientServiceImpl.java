package ru.moex.test;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Client service implementation
 */
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Client> getClientById(long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client saveClient(Client client) {
        //TODO here must by check if client already exists with same unique field
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getClients(String lastName, String firstName, String thirdName, String phone, String email) {
        return clientRepository.findAllByFields(lastName, firstName, thirdName, phone, email);
    }

}
