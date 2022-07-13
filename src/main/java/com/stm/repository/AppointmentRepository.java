package com.stm.repository;

import com.stm.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Override
    List<Appointment> findAll();
    Appointment getById(int id);

    List<Appointment> findByClientIdId(int id);
}