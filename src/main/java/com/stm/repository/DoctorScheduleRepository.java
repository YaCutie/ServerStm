package com.stm.repository;

import com.stm.Entity.Doctorsschedule;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface DoctorScheduleRepository extends CrudRepository<Doctorsschedule, Integer> {
    @Override
    List<Doctorsschedule> findAll();
    Doctorsschedule getById(int id);
    List<Doctorsschedule> findByPersonidIdOrderByDay(int id);
}