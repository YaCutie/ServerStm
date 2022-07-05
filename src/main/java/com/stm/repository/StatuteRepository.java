package com.stm.repository;

import com.stm.Entity.PersonalService;
import com.stm.Entity.Statute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatuteRepository extends JpaRepository<Statute, Integer> {
    @Override
    List<Statute> findAll();
    Statute getById(int id);
}

