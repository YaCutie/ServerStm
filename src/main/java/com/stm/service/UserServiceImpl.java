package com.stm.service;

import com.stm.Entity.Client;
import com.stm.Entity.Personal;
import com.stm.dto.GetClientByIdRsDto;
import com.stm.repository.ClientRepository;
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

    @Override
    public GetClientByIdRsDto getById(int id) {
        GetClientByIdRsDto getClientByIdRsDto = new GetClientByIdRsDto();
        getClientByIdRsDto.setUser(clientRepository.getById(id));
        return getClientByIdRsDto;
    }

    @Override
    public List<Personal> getAllPersonal() {
        List<Personal> list =  personalRepository.findAll();
        return list;
    }

    @Override
    public List<Client> getList() {
        return clientRepository.findAll();
    }
}
