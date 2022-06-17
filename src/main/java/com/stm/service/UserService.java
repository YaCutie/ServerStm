package com.stm.service;

import com.stm.Entity.Client;
import com.stm.Entity.Personal;
import com.stm.dto.GetClientByIdRsDto;

import java.util.List;

public interface UserService {
    List<Client> getList();

    GetClientByIdRsDto getById(int id);

    List<Personal> getAllPersonal();
}
