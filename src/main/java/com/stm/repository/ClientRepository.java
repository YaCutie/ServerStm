package com.stm.repository;

import com.stm.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Override
    List<Client> findAll();
    Client getByLogin(String login);
    Client getClientById(int id);
    Client getClientByLogin(String login);
}
