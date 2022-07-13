package com.stm.autentification;

import com.stm.Entity.Client;
import com.stm.Entity.Statute;
import com.stm.Entity.TokenData;
import com.stm.Entity.UsersToken;
import com.stm.dto.LoginRqDto;
import com.stm.dto.LoginRsDto;
import com.stm.dto.RegistrationRqDto;
import com.stm.dto.RegistrationRsDto;
import com.stm.repository.*;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Date;

@Service
public class AuthenticationServicempl implements AuthenticationService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    PersonalServiceRepository personalServiceRepository;
    @Autowired
    UsersTokenRepository usersTokenRepository;
    @Autowired
    StatuteRepository statuteRepository;

    @Override
    public LoginRsDto verification(LoginRqDto loginRqDto) {

        Map<String, Object> tokenData = new HashMap<>();

        boolean verification = false;
        String token = "";
        LoginRsDto loginRsDto = LoginRsDto.builder().build();

        String rqLogin = loginRqDto.getLogin();
        String rqPassword = loginRqDto.getPassword();

        try {
            Client cl = clientRepository.getByLogin(rqLogin);
            String userLogin = cl.getLogin();
            String userPassword = cl.getPassword();

            if (rqLogin.equalsIgnoreCase(userLogin) && rqPassword.equalsIgnoreCase(userPassword)) {
                tokenData.put(TokenData.CLIENT.getTokenData(), "user");
                tokenData.put(TokenData.ID.getTokenData(), cl.getId());
                tokenData.put(TokenData.NAME.getTokenData(), cl.getName());
                tokenData.put(TokenData.CREATE_DATE.getTokenData(), new Date().getTime());
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.YEAR, 100);
                tokenData.put(TokenData.EXPIRATION_DATE.getTokenData(), calendar.getTime());
                JwtBuilder jwtBuilder = Jwts.builder();
                jwtBuilder.setExpiration(calendar.getTime());
                jwtBuilder.setClaims(tokenData);
                String key = "abc123";
                token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
                usersTokenRepository.getUsersTokenList().add(new UsersToken(rqLogin, "Bearer " + token));

                verification = true;
                loginRsDto.setVerification(verification);
                loginRsDto.setToken(token);
                loginRsDto.setId(cl.getId());
                return loginRsDto;
            }
            loginRsDto.setVerification(verification);
            return loginRsDto;
        } catch (Error e) {
            return null;
        }
    }

    @Override
    public RegistrationRsDto registration(RegistrationRqDto registrationRqDto) {
        boolean verefication = true;
        Map<String, Object> tokenData = new HashMap<>();
        String token = "";
//        Client newClient;

        String rqLogin = registrationRqDto.getLogin();
        String rqEmail = registrationRqDto.getEmail();

        try {

            for (Client cl : clientRepository.findAll()) {
                String userLogin = cl.getLogin();
                String userEmail = cl.getEmail();

                if (rqLogin.equalsIgnoreCase(userLogin) || rqEmail.equalsIgnoreCase(userEmail)) {
                    RegistrationRsDto registrationRsDto = RegistrationRsDto.builder().build();
                    verefication = false;
                    registrationRsDto.setVerification(verefication);
                    return registrationRsDto;
                }
            }

//            newClient = clientRepository.save(new Client(registrationRqDto.getSurname(), registrationRqDto.getName(),
//                    registrationRqDto.getMiddleName(), registrationRqDto.getDateOfBirth(), registrationRqDto.getPhone(), registrationRqDto.getEmail(),
//                    registrationRqDto.getLogin().toLowerCase(Locale.ROOT), registrationRqDto.getPassword().toLowerCase(Locale.ROOT)));

//            clientRepository.save(new Client(registrationRqDto.getSurname(), registrationRqDto.getName(),
//                    registrationRqDto.getMiddleName(), registrationRqDto.getDateOfBirth(), registrationRqDto.getPhone(), registrationRqDto.getEmail(),
//                    registrationRqDto.getLogin().toLowerCase(Locale.ROOT), registrationRqDto.getPassword().toLowerCase(Locale.ROOT)));

            clientRepository.save((Client.builder().surname(registrationRqDto.getSurname()).name(registrationRqDto.getName()).middlename(registrationRqDto.getMiddleName())
                    .dateOfBirth(registrationRqDto.getDateOfBirth()).phone(registrationRqDto.getPhone()).email(registrationRqDto.getEmail())
                    .login(registrationRqDto.getLogin()).password(registrationRqDto.getPassword()).build()));
            tokenData.put(TokenData.CLIENT.getTokenData(), "user");
//            tokenData.put("userID", newClient.getId());
            tokenData.put(TokenData.ID.getTokenData(), clientRepository.getClientByLogin(rqLogin).getId());
            tokenData.put(TokenData.NAME.getTokenData(), registrationRqDto.getSurname() + " " + registrationRqDto.getName() + " " + registrationRqDto.getMiddleName());
            tokenData.put(TokenData.CREATE_DATE.getTokenData(), new Date().getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 100);
            tokenData.put(TokenData.EXPIRATION_DATE.getTokenData(), calendar.getTime());
            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(calendar.getTime());
            jwtBuilder.setClaims(tokenData);
            String key = "abc123";
            token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
            usersTokenRepository.getUsersTokenList().add(new UsersToken(rqLogin, "Bearer " + token));
        } catch (Error e) {
            RegistrationRsDto registrationRsDto = RegistrationRsDto.builder().build();
            verefication = false;
            registrationRsDto.setVerification(verefication);
            return registrationRsDto;
        }

        return RegistrationRsDto.builder().verification(verefication).token(token).id(clientRepository.getClientByLogin(rqLogin).getId()).build();
    }

    @Override
    public String DecodeTokenDate(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(chunks[1]));
        String[] payloadData = payload.split(",");
        String[] date = payloadData[1].split(":");
        return date[1];
    }

    private String encodeB64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decodeB64(String data) {
        return Base64.getDecoder().decode(data);
    }
}

