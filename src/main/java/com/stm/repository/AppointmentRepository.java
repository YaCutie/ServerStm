package com.stm.repository;

import com.stm.Entity.Appointment;
import com.stm.Entity.Personal;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
    @Override
    List<Appointment> findAll();
    Appointment getById(int id);
}