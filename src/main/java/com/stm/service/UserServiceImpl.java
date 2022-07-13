package com.stm.service;

import com.stm.Entity.*;
import com.stm.dto.GetAllAppointmentByUserIdRqDto;
import com.stm.dto.GetClientByIdRsDto;
import com.stm.dto.NewAppointmentRqDto;
import com.stm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    PersonalServiceRepository personalServiceRepository;
    @Autowired
    DoctorScheduleRepository doctorScheduleRepository;
    @Autowired
    StatuteRepository statuteRepository;
    @Autowired
    ClinicRepository clinicRepository;

    @Override
    public GetClientByIdRsDto getById(int id) {
        GetClientByIdRsDto getClientByIdRsDto = new GetClientByIdRsDto();
        getClientByIdRsDto.setСlient(clientRepository.getClientById(id));
        return getClientByIdRsDto;
    }

    @Override
    public List<Personal> getAllPersonal() {
        return personalRepository.findAll();
    }

    @Override
    public List<PersonalService> getAllServiceByPersonal(int id) {
        return personalServiceRepository.findAllByPersonalIdOrderById(id);
    }

    @Override
    public List<Doctorsschedule> getAllDoctorsscheduleByPersonal(int id) {
        return doctorScheduleRepository.findByPersonIdIdOrderByDay(id);
    }

    @Override
    public Personal getPersonalById(int id) {
        return personalRepository.getPersonalById(id);
    }

    @Override
    public boolean NewAppoitment(NewAppointmentRqDto newAppointmentRqDto) throws ParseException {
        Appointment newAppointment = new Appointment(newAppointmentRqDto.getReceptionTime(), clientRepository.getClientById(newAppointmentRqDto.getClientId()),
                clinicRepository.getClinicById(newAppointmentRqDto.getClinicId()), personalRepository.getPersonalById(newAppointmentRqDto.getPersonalId()),
                newAppointmentRqDto.getCabinetNumber(), statuteRepository.getStatuteById(newAppointmentRqDto.getStatus()));
        try {
            appointmentRepository.save(newAppointment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Appointment> GetAllAppointmentByUserId(GetAllAppointmentByUserIdRqDto getAllAppointmentByUserIdRqDto) {
        return appointmentRepository.findByClientIdId(getAllAppointmentByUserIdRqDto.getId());
    }
}
