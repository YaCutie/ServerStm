package com.stm.service;

import com.stm.Entity.Client;
import com.stm.autentification.AuthenticationServicempl;
import com.stm.dto.LoginRqDto;
import com.stm.dto.LoginRsDto;
import com.stm.dto.RegistrationRqDto;
import com.stm.dto.RegistrationRsDto;
import com.stm.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    ClientRepository clientRepository;
    @Mock
    PersonalRepository personalRepository;
    @Mock
    PersonalServiceRepository personalServiceRepository;
    @Mock
    UsersTokenRepository usersTokenRepository;

    @InjectMocks
    AuthenticationServicempl authenticationServicempl;


    @Test
    public void shouldVerificationTrue(){
        Client client = new Client("asd","asd","asd", LocalDate.now(),"12345","asd","zab","123");
        client.setId(1);
        LoginRqDto loginRqDto = LoginRqDto.builder().login("zab").password("123").build();
        when(clientRepository.getByLogin(anyString())).thenReturn(client);

        LoginRsDto loginRsDto = authenticationServicempl.verification(loginRqDto);

        assertTrue(loginRsDto.isVerification());
        verify(clientRepository).getByLogin(eq("zab"));
    }

    @Test
    public void shouldVerificationBadPassword(){
        Client client = new Client("asd","asd","asd", LocalDate.now(),"12345","asd","zab","123");
        client.setId(1);
        LoginRqDto loginRqDto = LoginRqDto.builder().login("zab").password("1234").build();
        when(clientRepository.getByLogin(anyString())).thenReturn(client);

        LoginRsDto loginRsDto = authenticationServicempl.verification(loginRqDto);

        assertFalse(loginRsDto.isVerification());
        verify(clientRepository).getByLogin(eq("zab"));
    }

    @Test
    public void shouldVerificationBadLogin(){
        Client client = new Client("asd","asd","asd", LocalDate.now(),"12345","asd","zab","123");
        client.setId(1);
        LoginRqDto loginRqDto = LoginRqDto.builder().login("zab1").password("123").build();
        when(clientRepository.getByLogin(anyString())).thenReturn(client);

        LoginRsDto loginRsDto = authenticationServicempl.verification(loginRqDto);

        assertFalse(loginRsDto.isVerification());
        verify(clientRepository).getByLogin(eq("zab1"));
    }

    @Test
    public void shouldRegistrationTrue(){
        Client client = new Client("asd","asd","asd", LocalDate.now(),"12345","asd@mail","zab","123");
        client.setId(1);
        List<Client> list = new ArrayList<>();
        list.add(client);
        Client client2 = new Client("asd","asd","asd", LocalDate.now(),"12345","asd1@mail","zab1","123");
        client2.setId(2);
        RegistrationRqDto registrationRqDto = RegistrationRqDto.builder().surname("asd").name("asd").middleName("asd").dateOfBirth(LocalDate.now())
                .phone("12345").email("asd1@mail").login("zab1").password("123").build();
        when(clientRepository.findAll()).thenReturn(list);
        when(clientRepository.save(any(Client.class))).thenReturn(client2);
        when(clientRepository.getClientByLogin(anyString())).thenReturn(client2);

        RegistrationRsDto registrationRsDto = authenticationServicempl.registration(registrationRqDto);

        assertTrue(registrationRsDto.isVerification());
        assertEquals(2, registrationRsDto.getId());
        verify(clientRepository,times(2)).getClientByLogin(eq("zab1"));
    }

    @Test
    public void shouldRegistrationNoClients(){
        List<Client> list = new ArrayList<>();
        Client client2 = new Client("asd","asd","asd", LocalDate.now(),"12345","asd1@mail","zab1","123");
        client2.setId(2);
        RegistrationRqDto registrationRqDto = RegistrationRqDto.builder().surname("asd").name("asd").middleName("asd").dateOfBirth(LocalDate.now())
                .phone("12345").email("asd1@mail").login("zab1").password("123").build();
        when(clientRepository.findAll()).thenReturn(list);
        when(clientRepository.save(any(Client.class))).thenReturn(client2);
        when(clientRepository.getClientByLogin(anyString())).thenReturn(client2);

        RegistrationRsDto registrationRsDto = authenticationServicempl.registration(registrationRqDto);

        assertTrue(registrationRsDto.isVerification());
        assertEquals(2, registrationRsDto.getId());
        verify(clientRepository,times(2)).getClientByLogin(eq("zab1"));
    }

    @Test
    public void shouldRegistrationBadEmailOrLogin(){
        Client client = new Client("asd","asd","asd", LocalDate.now(),"12345","asd@mail","zab","123");
        client.setId(1);
        List<Client> list = new ArrayList<>();
        list.add(client);
        RegistrationRqDto registrationRqDto = RegistrationRqDto.builder().surname("asd").name("asd").middleName("asd").dateOfBirth(LocalDate.now())
                .phone("12345").email("asd@mail").login("zab").password("123").build();
        when(clientRepository.findAll()).thenReturn(list);

        RegistrationRsDto registrationRsDto = authenticationServicempl.registration(registrationRqDto);

        assertFalse(registrationRsDto.isVerification());
    }
}
