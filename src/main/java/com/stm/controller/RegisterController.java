package com.stm.controller;

import com.stm.autentification.AuthenticationServicempl;
import com.stm.dto.LoginRqDto;
import com.stm.dto.LoginRsDto;
import com.stm.dto.RegistrationRqDto;
import com.stm.dto.RegistrationRsDto;
import com.stm.service.UserService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;


@Secured(SecurityRule.IS_ANONYMOUS)
@Controller
public class RegisterController {

    @Inject
    UserService userService;
    @Inject
    private AuthenticationServicempl authenticationService;

    @Post(value = "/user/login", consumes = MediaType.APPLICATION_JSON)
    public LoginRsDto Verification(@Body LoginRqDto loginRqDto){
        return authenticationService.Verification(loginRqDto);
    }
    @Post(value = "/user/registration", consumes = MediaType.APPLICATION_JSON)
    public RegistrationRsDto Registration(@Body RegistrationRqDto registrationRqDto){
        return authenticationService.Registration(registrationRqDto);
    }
}
