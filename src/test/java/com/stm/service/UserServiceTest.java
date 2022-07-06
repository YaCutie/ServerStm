package com.stm.service;

import com.stm.Entity.Client;
import com.stm.autentification.AuthenticationServicempl;
import com.stm.dto.GetClientByIdRsDto;
import com.stm.dto.LoginRqDto;
import com.stm.dto.LoginRsDto;
import com.stm.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    ClientRepository clientRepository;
    @Mock
    AppointmentRepository appointmentRepository;
    @Mock
    PersonalRepository personalRepository;
    @Mock
    PersonalServiceRepository personalServiceRepository;
    @Mock
    DoctorScheduleRepository doctorScheduleRepository;
    @Mock
    StatuteRepository statuteRepository;
    @Mock
    ClinicRepository clinicRepository;

    @InjectMocks
    UserServiceImpl userService;


    @Test
    public void shouldGetClientByIdTrue(){
        Client client = new Client("asd","asd","asd", LocalDate.now(),"12345","asd","zab","123");
        client.setId(1);
        int id = 1;
        when(clientRepository.getClientById(anyInt())).thenReturn(client);

        GetClientByIdRsDto getClientByIdRsDto = userService.getById(id);

        assertEquals(client, getClientByIdRsDto.getСlient());
        verify(clientRepository).getClientById(eq(1));
    }

    @Test
    public void shouldGetClientByIdFalse(){
        int id = 1;
        when(clientRepository.getClientById(anyInt())).thenReturn(null);

        GetClientByIdRsDto getClientByIdRsDto = userService.getById(id);

        assertNull(getClientByIdRsDto.getСlient());
        verify(clientRepository).getClientById(eq(1));
    }

}
