package com.stm.repository;

import com.stm.Entity.Clinic;
import com.stm.Entity.Statute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
    @Override
    List<Clinic> findAll();
    Clinic getClinicById(int id);
}