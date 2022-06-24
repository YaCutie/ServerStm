package com.stm.repository;

import com.stm.Entity.PersonalService;
import com.stm.Entity.Statute;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface StatuteRepository extends CrudRepository<Statute, Integer> {
    @Override
    List<Statute> findAll();
    Statute getById(int id);
}

