package com.stm.repository;

import com.stm.Entity.Client;
import com.stm.Entity.Clinic;
import com.stm.Entity.Personal;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface PersonalRepository extends CrudRepository<Personal, Integer> {
    @Override
    List<Personal> findAll();
    Personal getById(int id);
    List<Personal> findAllOrderByExperience();

}