package ru.moex.test;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Client repository
 */
public interface ClientRepository extends JpaRepository<Client, Long>, CustomClientRepository {

}
