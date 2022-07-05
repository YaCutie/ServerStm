package com.stm.autentification;

import com.stm.Entity.Client;
import com.stm.Entity.Statute;
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

    @Autowired
    public void main() {
    }

//    @Override
//    public Statute GetStatuteById(Integer id){
//        return statuteRepository.getStatuteById(id);
//    }

    @Override
    public LoginRsDto Verification(LoginRqDto loginRqDto) {

        Map<String, Object> tokenData = new HashMap<>();

        boolean ver = false;
        String token = "";
        LoginRsDto loginRsDto = LoginRsDto.builder().build();

        String rqLogin = loginRqDto.getLogin();
        String rqPassword = loginRqDto.getPassword();

        try {
            Client cl = clientRepository.getByLogin(rqLogin);
            String userLogin = cl.getLogin();
            String userPassword = cl.getPassword();

            if (rqLogin.equals(userLogin) && rqPassword.equals(userPassword)) {

                tokenData.put("clientType", "user");
                tokenData.put("userID", cl.getId());
                tokenData.put("username", cl.getName());
                tokenData.put("token_create_date", new Date().getTime());
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.YEAR, 100);
                tokenData.put("token_expiration_date", calendar.getTime());
                JwtBuilder jwtBuilder = Jwts.builder();
                jwtBuilder.setExpiration(calendar.getTime());
                jwtBuilder.setClaims(tokenData);
                String key = "abc123";
                token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
                usersTokenRepository.getUsersTokenList().add(new UsersToken(rqLogin, "Bearer " + token));

                ver = true;
                loginRsDto.setVerification(ver);
                loginRsDto.setToken(token);
                loginRsDto.setId(cl.getId());
                return loginRsDto;
            }
            loginRsDto.setVerification(ver);
            return loginRsDto;
        } catch (Error e) {
            return null;
        }
    }

    @Override
    public RegistrationRsDto Registration(RegistrationRqDto registrationRqDto) {
        boolean ver = true;
        Map<String, Object> tokenData = new HashMap<>();
        String token = "";

        String rqLogin = registrationRqDto.getLogin().toLowerCase(Locale.ROOT);
        String rqEmail = registrationRqDto.getEmail().toLowerCase(Locale.ROOT);

        for (Client cl : clientRepository.findAll()) {
            String userLogin = cl.getLogin().toLowerCase(Locale.ROOT);
            String userEmail = cl.getEmail().toLowerCase(Locale.ROOT);

            if (rqLogin.equals(userLogin) || rqEmail.equals(userEmail)) {
                RegistrationRsDto registrationRsDto = RegistrationRsDto.builder().build();
                ver = false;
                registrationRsDto.setVerification(ver);
                return registrationRsDto;
            }
        }

        clientRepository.save(new Client(registrationRqDto.getSurname(), registrationRqDto.getName(),
                registrationRqDto.getMiddleName(), registrationRqDto.getDateOfBirth(),registrationRqDto.getPhone(), registrationRqDto.getEmail(),
                registrationRqDto.getLogin().toLowerCase(Locale.ROOT), registrationRqDto.getPassword().toLowerCase(Locale.ROOT)));

        tokenData.put("clientType", "user");
        tokenData.put("userID", clientRepository.getClientByLogin(rqLogin).getId());
        tokenData.put("username", registrationRqDto.getSurname() + " " +registrationRqDto.getName() + " " + registrationRqDto.getMiddleName());
        tokenData.put("token_create_date", new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 100);
        tokenData.put("token_expiration_date", calendar.getTime());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        String key = "abc123";
        token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
        usersTokenRepository.getUsersTokenList().add(new UsersToken(rqLogin,"Bearer "+token));

//        RegistrationRsDto registrationRsDto = RegistrationRsDto.builder().build();
//        registrationRsDto.setVerification(ver);
//        registrationRsDto.setToken(token);
//        registrationRsDto.setId(clientRepository.getClientByLogin(rqLogin).getId());
//        return registrationRsDto;

        return RegistrationRsDto.builder().verification(ver).token(token).id(clientRepository.getClientByLogin(rqLogin).getId()).build();
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

