package com.stm.service;

import com.stm.Entity.Client;
import com.stm.Entity.Doctorsschedule;
import com.stm.Entity.Personal;
import com.stm.Entity.PersonalService;
import com.stm.dto.GetClientByIdRsDto;

import java.util.List;

public interface UserService {
    List<Client> getList();

    GetClientByIdRsDto getById(int id);

    List<Personal> getAllPersonal();

    List<PersonalService> getAllServiceByPersonal(int id);

    List<Doctorsschedule> getAllDoctorsscheduleByPersonal(int id);

    Personal getPersonalById(int id);
}
