package com.stm.service;

import com.stm.Entity.*;
import com.stm.dto.GetAllAppointmentByUserIdRqDto;
import com.stm.dto.GetClientByIdRsDto;
import com.stm.dto.NewAppointmentRqDto;
import com.stm.dto.SendFileRqDto;
import com.stm.repository.*;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.text.ParseException;
import java.util.*;

@Singleton
public class UserServiceImpl implements UserService {

    @Inject
    ClientRepository clientRepository;
    @Inject
    AppointmentRepository appointmentRepository;
    @Inject
    PersonalRepository personalRepository;
    @Inject
    PersonalServiceRepository personalServiceRepository;
    @Inject
    DoctorScheduleRepository doctorScheduleRepository;
    @Inject
    StatuteRepository statuteRepository;
    @Inject
    ClinicRepository clinicRepository;

    @Override
    public GetClientByIdRsDto getById(int id) {
        GetClientByIdRsDto getClientByIdRsDto = new GetClientByIdRsDto();
        getClientByIdRsDto.setСlient(clientRepository.getById(id));
        return getClientByIdRsDto;
    }

    @Override
    public List<Personal> getAllPersonal() {
        List<Personal> list =  personalRepository.findAllOrderByExperience();
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
    public boolean NewAppoitment(NewAppointmentRqDto newAppointmentRqDto) throws ParseException {
        Appointment newAppointment = new Appointment(newAppointmentRqDto.getReceptionTime(), clientRepository.getById(newAppointmentRqDto.getClientid()),
                clinicRepository.getById(newAppointmentRqDto.getClinicid()), personalRepository.getById(newAppointmentRqDto.getPersonalid()),
                newAppointmentRqDto.getCabinetNumber(), statuteRepository.getById(newAppointmentRqDto.getStatus()));
        appointmentRepository.save(newAppointment);
        return true;
    }

    @Override
    public List<Appointment> GetAllAppointmentByUserId(GetAllAppointmentByUserIdRqDto getAllAppointmentByUserIdRqDto) {
        return appointmentRepository.findByClientidId(getAllAppointmentByUserIdRqDto.getId());
    }

    @Override
    public List<Client> getList() {
        return clientRepository.findAll();
    }
}
