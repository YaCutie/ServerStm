package com.stm.service;

import com.stm.Entity.Client;
import com.stm.Entity.Doctorsschedule;
import com.stm.Entity.Personal;
import com.stm.Entity.PersonalService;
import com.stm.dto.GetClientByIdRsDto;
import com.stm.repository.ClientRepository;
import com.stm.repository.DoctorScheduleRepository;
import com.stm.repository.PersonalRepository;
import com.stm.repository.PersonalServiceRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;


import java.util.*;

@Singleton
public class UserServiceImpl implements UserService {

    @Inject
    ClientRepository clientRepository;

    @Inject
    PersonalRepository personalRepository;

    @Inject
    PersonalServiceRepository personalServiceRepository;

    @Inject
    DoctorScheduleRepository doctorScheduleRepository;

    @Override
    public GetClientByIdRsDto getById(int id) {
        GetClientByIdRsDto getClientByIdRsDto = new GetClientByIdRsDto();
        getClientByIdRsDto.setСlient(clientRepository.getById(id));
        return getClientByIdRsDto;
    }

    @Override
    public List<Personal> getAllPersonal() {
        List<Personal> list =  personalRepository.findAll();
        return list;
    }

    @Override
    public List<PersonalService> getAllServiceByPersonal(int id) {
        return personalServiceRepository.findAllByPersonalIdOrderById(id);
    }

    @Override
    public List<Doctorsschedule> getAllDoctorsscheduleByPersonal(int id) {
        return doctorScheduleRepository.findByPersonidIdOrderByDay(id);
    }

    @Override
    public Personal getPersonalById(int id) {
        return personalRepository.getById(id);
    }

    @Override
    public List<Client> getList() {
        return clientRepository.findAll();
    }
}
