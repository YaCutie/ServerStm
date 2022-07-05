package com.stm.service;

import com.stm.Entity.Client;
import com.stm.Entity.Statute;
import com.stm.autentification.AuthenticationServicempl;
import com.stm.dto.LoginRqDto;
import com.stm.dto.LoginRsDto;
import com.stm.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    @Mock
    StatuteRepository statuteRepository;

    @InjectMocks
    AuthenticationServicempl authenticationServicempl;

    @Test
    public void shouldGetStatuteById(){
        Statute statute = new Statute(0,"Новая");
        when(statuteRepository.getStatuteById(anyInt())).thenReturn(statute);

        Statute newStatute = authenticationServicempl.GetStatuteById(0);

        assertEquals("Новая", newStatute.getStatusname());
        verify(statuteRepository).getStatuteById(eq(0));
    }

    @Test
    public void shouldVerificationTrue(){
        Client client = new Client("asd","asd","asd", LocalDate.now(),"12345","asd","zab","123");
        client.setId(1);
        LoginRqDto loginRqDto = LoginRqDto.builder().login("zab").password("123").build();
        when(clientRepository.getByLogin(anyString())).thenReturn(client);

        LoginRsDto loginRsDto = authenticationServicempl.Verification(loginRqDto);

        assertEquals(true, loginRsDto.isVerification());
        verify(clientRepository).getByLogin(eq("zab"));
    }

    @Test
    public void shouldVerificationBadPassword(){
        Client client = new Client("asd","asd","asd", LocalDate.now(),"12345","asd","zab","123");
        client.setId(1);
        LoginRqDto loginRqDto = LoginRqDto.builder().login("zab").password("1234").build();
        when(clientRepository.getByLogin(anyString())).thenReturn(client);

        LoginRsDto loginRsDto = authenticationServicempl.Verification(loginRqDto);

        assertEquals(false, loginRsDto.isVerification());
        verify(clientRepository).getByLogin(eq("zab"));
    }

    @Test
    public void shouldVerificationBadLogin(){
        Client client = new Client("asd","asd","asd", LocalDate.now(),"12345","asd","zab","123");
        client.setId(1);
        LoginRqDto loginRqDto = LoginRqDto.builder().login("zab1").password("123").build();
        when(clientRepository.getByLogin(anyString())).thenReturn(client);

        LoginRsDto loginRsDto = authenticationServicempl.Verification(loginRqDto);

        assertEquals(false, loginRsDto.isVerification());
        verify(clientRepository).getByLogin(eq("zab1"));
    }

}
