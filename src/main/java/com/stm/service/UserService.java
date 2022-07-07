package com.stm.service;

import com.stm.Entity.*;
import com.stm.dto.GetAllAppointmentByUserIdRqDto;
import com.stm.dto.GetClientByIdRsDto;
import com.stm.dto.NewAppointmentRqDto;
import com.stm.dto.SendFileRqDto;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    GetClientByIdRsDto getById(int id);

    List<Personal> getAllPersonal();

    List<PersonalService> getAllServiceByPersonal(int id);

    List<Doctorsschedule> getAllDoctorsscheduleByPersonal(int id);

    Personal getPersonalById(int id);

    boolean NewAppoitment(NewAppointmentRqDto newAppointment) throws ParseException;

    List<Appointment> GetAllAppointmentByUserId(GetAllAppointmentByUserIdRqDto getAllAppointmentByUserIdRqDto);
}
