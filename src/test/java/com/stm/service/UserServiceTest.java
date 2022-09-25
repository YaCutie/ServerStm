package com.stm.service;

import com.stm.Entity.*;
import com.stm.dto.*;
import com.stm.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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

        assertEquals(client, getClientByIdRsDto.getClient());
        verify(clientRepository).getClientById(eq(1));
    }

    @Test
    public void shouldGetClientByIdFalse(){
        int id = 1;
        when(clientRepository.getClientById(anyInt())).thenReturn(null);

        GetClientByIdRsDto getClientByIdRsDto = userService.getById(id);

        assertNull(getClientByIdRsDto.getClient());
        verify(clientRepository).getClientById(eq(1));
    }

    @Test
    public void shouldGetAllServiceByPersonalTrue(){
        int id = 1;
        PersonalService personalService = PersonalService.builder().personal(Personal.builder().id(1).name("asd").build())
                .service(Service.builder().id(1).servicesName("Zub").build()).build();
        List<PersonalService> list = new ArrayList<>();
        list.add(personalService);
        when(personalServiceRepository.findAllByPersonalIdOrderById(anyInt())).thenReturn(list);

        List<PersonalService> all = userService.getAllServiceByPersonal(id);

        assertEquals(personalService, all.get(0));
        verify(personalServiceRepository).findAllByPersonalIdOrderById(eq(1));
    }

    @Test
    public void shouldGetAllServiceByPersonalFalse(){
        int id = 1;
        List<PersonalService> list = new ArrayList<>();
        when(personalServiceRepository.findAllByPersonalIdOrderById(anyInt())).thenReturn(list);

        List<PersonalService> all = userService.getAllServiceByPersonal(id);

        assertEquals(0, all.size());
        verify(personalServiceRepository).findAllByPersonalIdOrderById(eq(1));
    }
    @Test
    public void shouldGetAllDoctorsscheduleByPersonalTrue(){
        int id = 1;
        Doctorsschedule doctorsschedule = Doctorsschedule.builder().id(1).day(2).personId(Personal.builder().id(1).name("asd").build()).build();
        List<Doctorsschedule> list = new ArrayList<>();
        list.add(doctorsschedule);
        when(doctorScheduleRepository.findByPersonIdIdOrderByDay(anyInt())).thenReturn(list);

        List<Doctorsschedule> all = userService.getAllDoctorsscheduleByPersonal(id);

        assertEquals(doctorsschedule, all.get(0));
        verify(doctorScheduleRepository).findByPersonIdIdOrderByDay(eq(1));
    }

    @Test
    public void shouldGetAllDoctorsscheduleByPersonalFalse(){
        int id = 1;
        List<Doctorsschedule> list = new ArrayList<>();
        when(doctorScheduleRepository.findByPersonIdIdOrderByDay(anyInt())).thenReturn(list);

        List<Doctorsschedule> all = userService.getAllDoctorsscheduleByPersonal(id);

        assertEquals(0, all.size());
        verify(doctorScheduleRepository).findByPersonIdIdOrderByDay(eq(1));
    }

    @Test
    public void shouldGetPersonalByIdTrue(){
        Personal personal = Personal.builder().id(1).name("asd").build();
        int id = 1;
        when(personalRepository.getPersonalById(anyInt())).thenReturn(personal);

        Personal personal1 = userService.getPersonalById(id);

        assertEquals(personal, personal1);
        verify(personalRepository).getPersonalById(eq(1));
    }

    @Test
    public void shouldGetPersonalByIdFalse(){
        int id = 1;
        when(personalRepository.getPersonalById(anyInt())).thenReturn(null);

        Personal personal1 = userService.getPersonalById(id);

        assertNull(personal1);
        verify(personalRepository).getPersonalById(eq(1));
    }

    @Test
    public void shouldGetAllAppointmentByUserIdTrue(){
        Appointment appointment = Appointment.builder().clientId(Client.builder().id(1).build()).cabinetNumber(27).build();
        List<Appointment> list = new ArrayList<>();
        list.add(appointment);
        GetAllAppointmentByUserIdRqDto getAllAppointmentByUserIdRqDto = GetAllAppointmentByUserIdRqDto.builder().id(1).build();
        when(appointmentRepository.findByClientIdId(anyInt())).thenReturn(list);

        List<Appointment> all = userService.GetAllAppointmentByUserId(getAllAppointmentByUserIdRqDto);

        assertEquals(appointment, all.get(0));
        verify(appointmentRepository).findByClientIdId(eq(1));
    }

    @Test
    public void shouldGetAllAppointmentByUserIdFalse(){
        int id = 1;
        List<Appointment> list = new ArrayList<>();
        GetAllAppointmentByUserIdRqDto getAllAppointmentByUserIdRqDto = GetAllAppointmentByUserIdRqDto.builder().id(1).build();
        when(appointmentRepository.findByClientIdId(anyInt())).thenReturn(list);

        List<Appointment> all = userService.GetAllAppointmentByUserId(getAllAppointmentByUserIdRqDto);

        assertEquals(0, all.size());
        verify(appointmentRepository).findByClientIdId(eq(1));
    }

    @Test
    public void shouldNewAppoitmentTrue() throws ParseException {
        NewAppointmentRqDto newAppointmentRqDto = NewAppointmentRqDto.builder().receptionTime("2022-07-07 8:00").clientId(1).personalId(1)
                .clinicId(1).cabinetNumber(27).status(1).build();
        Client client = Client.builder().id(1).name("asd").build();
        Clinic clinic = Clinic.builder().id(1).clinicName("asd").build();
        Personal personal = Personal.builder().id(1).name("asd").build();
        Statute statute = Statute.builder().id(1).statusName("new").build();
        when(clientRepository.getClientById(anyInt())).thenReturn(client);
        when(clinicRepository.getClinicById(anyInt())).thenReturn(clinic);
        when(personalRepository.getPersonalById(anyInt())).thenReturn(personal);
        when(statuteRepository.getStatuteById(anyInt())).thenReturn(statute);
        when(appointmentRepository.save(any(Appointment.class))).then(AdditionalAnswers.returnsFirstArg());

        boolean ver = userService.NewAppoitment(newAppointmentRqDto);

        assertTrue(ver);
        verify(clientRepository).getClientById(eq(1));
        verify(clinicRepository).getClinicById(eq(1));
        verify(personalRepository).getPersonalById(eq(1));
        verify(statuteRepository).getStatuteById(eq(1));
        verify(appointmentRepository).save(argThat(saved -> saved.getStatus().getId().equals(1)));
    }

    @Test
    public void shouldNewAppoitmentFalse() throws ParseException {
        NewAppointmentRqDto newAppointmentRqDto = NewAppointmentRqDto.builder().receptionTime("2022-07-07 8:00").clientId(1).personalId(1)
                .clinicId(1).cabinetNumber(27).status(1).build();
        when(clientRepository.getClientById(anyInt())).thenReturn(null);
        when(clinicRepository.getClinicById(anyInt())).thenReturn(null);
        when(personalRepository.getPersonalById(anyInt())).thenReturn(null);
        when(statuteRepository.getStatuteById(anyInt())).thenReturn(null);
        when(appointmentRepository.save(any(Appointment.class))).thenThrow(DataIntegrityViolationException.class);

        boolean ver = userService.NewAppoitment(newAppointmentRqDto);

        assertFalse(ver);
        verify(appointmentRepository).save(argThat(saved -> saved.getStatus() == null));
    }
}
