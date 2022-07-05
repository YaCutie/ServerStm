package com.stm.repository;

import com.stm.Entity.Client;
import com.stm.Entity.Clinic;
import com.stm.Entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Integer> {
    @Override
    List<Personal> findAll();
    Personal getPersonalById(int id);
//    List<Personal> findAllOrderByExperience();
}