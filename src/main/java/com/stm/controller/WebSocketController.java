package com.stm.controller;

import com.stm.dto.GetClientByIdRqDto;
import com.stm.dto.GetClientByIdRsDto;
import com.stm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;

@Controller
public class WebSocketController {

    @Autowired
    UserService userService;

    @MessageMapping("/test")
    @SendTo("/topic/id")
    public GetClientByIdRsDto getById(@RequestBody GetClientByIdRqDto getUserByIdRqDto) throws SQLException {
        return userService.getById(getUserByIdRqDto.getId());
    }
}
