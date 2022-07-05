package com.stm.autentification;

import com.stm.Entity.Statute;
import com.stm.dto.LoginRqDto;
import com.stm.dto.LoginRsDto;
import com.stm.dto.RegistrationRqDto;
import com.stm.dto.RegistrationRsDto;

public interface AuthenticationService {

    LoginRsDto Verification(LoginRqDto loginRqDto);

    RegistrationRsDto Registration(RegistrationRqDto registrationRqDto);

    String DecodeTokenDate(String token);

}
