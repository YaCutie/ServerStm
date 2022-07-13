package com.stm.autentification;

import com.stm.dto.LoginRqDto;
import com.stm.dto.LoginRsDto;
import com.stm.dto.RegistrationRqDto;
import com.stm.dto.RegistrationRsDto;

public interface AuthenticationService {

    LoginRsDto verification(LoginRqDto loginRqDto);

    RegistrationRsDto registration(RegistrationRqDto registrationRqDto);

    String DecodeTokenDate(String token);

}
