package com.stm.repository;

import com.stm.Entity.Client;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
    @Override
    List<Client> findAll();
    Client getByLogin(String login);
    Client getById(int id);
    Integer getIdByLogin(String login);
}
