package ru.moex.test;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Client repository
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByLastNameOrFirstNameOrThirdNameOrPhoneOrEmail(String lastName, String firstName, String thirdName, String phone, String email);
}
