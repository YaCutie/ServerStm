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

//    @Override
//    public Integer SendFile(SendFileRqDto sendFileRqDto) {
//        final String from = "stmsend@mail.ru";
//        final String password = "pPM0k77wuuDNczV6WWyK";
//        String host = "smtp.mail.ru";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "465");
//        props.put("mail.smtps.ssl.checkserveridentity", true);
//        props.put("mail.smtps.ssl.trust", "*");
//        props.put("mail.smtp.ssl.enable", "true");
//
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPassAuth() {
//                        return new PasswordAuthentication(from, password);
//                    }
//                });
//
//        try {
//            // Создание объекта MimeMessage по умолчанию
//            MimeMessage message = new MimeMessage(session);
//
//            // Установить От: поле заголовка
//            message.setFrom(new InternetAddress(from));
//
//            // Установить Кому: поле заголовка
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendFileRqDto.getEmail()));
//
//            // Установить тему: поле заголовка
//            message.setSubject("Уведомление ");
//
//            // Создание части сообщения
//            BodyPart messageBodyPart = new MimeBodyPart();
//
//            // Заполнение сообщения
//            messageBodyPart.setText("Ваша заявка находится в рассмотрении" + "\n Дата: " + sendFileRqDto.getDate() + "\n Время: " + sendFileRqDto.getTime()
//                    + "\n Клиника: " + sendFileRqDto.getClinic() + "\n Врач: " + sendFileRqDto.getPerson() + "\n Статус: " + sendFileRqDto.getStatus());
//
//            // Создание составного сообщения
//            Multipart multipart = new MimeMultipart();
//
//            // Установить часть текстового сообщения
//            multipart.addBodyPart(messageBodyPart);
//
//            // Отправить полные части сообщения
//            message.setContent(multipart);
//
//            // Отправить сообщение
//            try {
//                Transport.send(message, from, password);
//            } catch (javax.mail.SendFailedException sendFailedException) {
//                return 2;
//            }
//            System.out.println("Сообщение успешно отправлено....");
//        } catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
//        return 1;
//    }


    @Override
    public List<Client> getList() {
        return clientRepository.findAll();
    }
}
