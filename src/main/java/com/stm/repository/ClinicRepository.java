package com.stm.repository;

import com.stm.Entity.Clinic;
import com.stm.Entity.Statute;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface ClinicRepository extends CrudRepository<Clinic, Integer> {
    @Override
    List<Clinic> findAll();
    Clinic getById(int id);
}