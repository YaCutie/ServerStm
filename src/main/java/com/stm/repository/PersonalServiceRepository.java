package com.stm.repository;

import com.stm.Entity.PersonalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalServiceRepository extends JpaRepository<PersonalService, Integer> {
    @Override
    List<PersonalService> findAll();
    PersonalService getById(int id);
    List<PersonalService> findAllByPersonalIdOrderById(int id);
}
