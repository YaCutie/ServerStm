package com.stm.repository;

import com.stm.Entity.Doctorsschedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorScheduleRepository extends JpaRepository<Doctorsschedule, Integer> {
    @Override
    List<Doctorsschedule> findAll();
    Doctorsschedule getById(int id);
    List<Doctorsschedule> findByPersonIdIdOrderByDay(int id);
}