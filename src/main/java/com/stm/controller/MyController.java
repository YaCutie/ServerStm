package com.stm.controller;

import com.stm.Entity.*;
import com.stm.autentification.AuthenticationService;
import com.stm.dto.*;
import com.stm.repository.UsersTokenRepository;
import com.stm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.text.ParseException;
import java.util.List;

@RestController
public class MyController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UsersTokenRepository usersTokenRepository;

    @PostMapping(path="/user/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetClientByIdRsDto getById(@RequestBody GetClientByIdRqDto getUserByIdRqDto) throws SQLException {
        return userService.getById(getUserByIdRqDto.getId());
    }
    @GetMapping( "/personal/findall")
    public List<Personal>  getAllPersonal() throws SQLException {
        return userService.getAllPersonal();
    }
    @PostMapping(path= "/personal/findallservices", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonalService> getAllServiceByPersonal(@RequestBody GetAllServicesByPersonalIdRqDto getAllServicesByPersonalIdRqDto) throws SQLException {
        return userService.getAllServiceByPersonal(getAllServicesByPersonalIdRqDto.getId());
    }
    @PostMapping(path="/user/newappoitment", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean NewAppointment(@RequestBody NewAppointmentRqDto newAppointmentRqDto) throws SQLException, ParseException {
        boolean accept = false;
        accept = userService.NewAppoitment(newAppointmentRqDto);
        return accept;
    }
    @PostMapping(path= "/user/allappointment", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Appointment> getAllallappointmentByUserId(@RequestBody GetAllAppointmentByUserIdRqDto getAllAppointmentByUserIdRqDto) throws SQLException, ParseException {
        return userService.GetAllAppointmentByUserId(getAllAppointmentByUserIdRqDto);
    }
    @PostMapping(path="/personal/findallschedule", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Doctorsschedule> getAllDoctorsscheduleByPersonal(@RequestBody GetAllDoctorsScheduleByPersonalIdRqDto getAllDoctorsScheduleByPersonalIdRqDto) throws SQLException {
        return userService.getAllDoctorsscheduleByPersonal(getAllDoctorsScheduleByPersonalIdRqDto.getId());
    }
    @PostMapping(path="/personal/findpersonbyid", produces = MediaType.APPLICATION_JSON_VALUE)
    public Personal getPersonalById(@RequestBody GetAllDoctorsScheduleByPersonalIdRqDto getAllDoctorsScheduleByPersonalIdRqDto) throws SQLException {
        return userService.getPersonalById(getAllDoctorsScheduleByPersonalIdRqDto.getId());
    }
}
