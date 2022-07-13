package com.stm.controller;

import com.stm.autentification.AuthenticationServicempl;
import com.stm.dto.LoginRqDto;
import com.stm.dto.LoginRsDto;
import com.stm.dto.RegistrationRqDto;
import com.stm.dto.RegistrationRsDto;
import com.stm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegisterController {

    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationServicempl authenticationService;

    @PostMapping(value = "/user/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginRsDto Verification(@RequestBody LoginRqDto loginRqDto){
        return authenticationService.verification(loginRqDto);
    }
    @PostMapping(value = "/user/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public RegistrationRsDto Registration(@RequestBody RegistrationRqDto registrationRqDto){
        return authenticationService.registration(registrationRqDto);
    }
}
