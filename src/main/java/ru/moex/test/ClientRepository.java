package ru.moex.test;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Client repository
 */
public interface ClientRepository extends CrudRepository<Client, Long> {

    public Optional<Client> findById(long id);

    public List<Client> findByLastNameOrFirstNameOrThirdNameOrPhoneOrEmail(String lastName, String firstName, String thirdName, String phone, String email);
//    public List<Client> findByLastName(String lastName);
//    public List<Client> findByFirstName(String firstName);
//    public List<Client> findByThirdName(String thirdName);
//    public List<Client> findByPhone(String phone);
//    public List<Client> findByEmail(String email);
}
