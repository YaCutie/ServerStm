package com.stm.repository;

import com.stm.Entity.PersonalService;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface PersonalServiceRepository extends CrudRepository<PersonalService, Integer> {
    @Override
    List<PersonalService> findAll();
    PersonalService getById(int id);
    List<PersonalService> findAllByPersonalIdOrderById(int id);
}
