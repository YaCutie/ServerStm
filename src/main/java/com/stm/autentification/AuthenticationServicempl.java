package com.stm.autentification;

import com.stm.Entity.Client;
import com.stm.Entity.UsersToken;
import com.stm.dto.LoginRqDto;
import com.stm.dto.LoginRsDto;
import com.stm.dto.RegistrationRqDto;
import com.stm.dto.RegistrationRsDto;
import com.stm.repository.ClientRepository;
import com.stm.repository.PersonalRepository;
import com.stm.repository.PersonalServiceRepository;
import com.stm.repository.UsersTokenRepository;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

@Singleton
public class AuthenticationServicempl implements AuthenticationService {
    @Inject
    ClientRepository clientRepository;

    @Inject
    PersonalRepository personalRepository;

    @Inject
    PersonalServiceRepository personalServiceRepository;

    @Inject
    UsersTokenRepository usersTokenRepository;

    @Inject
    public void main() {

    }

    @Override
    public LoginRsDto Verification(LoginRqDto loginRqDto) {

        Map<String, Object> tokenData = new HashMap<>();

        boolean ver = false;
        String token = "";
        String rqLogin = loginRqDto.getLogin();
        String rqPassword = loginRqDto.getPassword();

        try {
            Client cl = clientRepository.getByLogin(rqLogin);
            String userLogin = cl.getLogin();
            String userPassword = cl.getPassword();

            if (rqLogin.equals(userLogin)) {
                if (rqPassword.equals(userPassword)) {
                    ver = true;

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
                }

            }
            LoginRsDto loginRsDto = new LoginRsDto();
            loginRsDto.setVerification(ver);
            loginRsDto.setToken(token);

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
                RegistrationRsDto registrationRsDto = new RegistrationRsDto();
                ver = false;
                registrationRsDto.setVerification(ver);
                registrationRsDto.setToken(token);
                return registrationRsDto;
            }
        }

        clientRepository.save(new Client(registrationRqDto.getSurname(), registrationRqDto.getName(),
                registrationRqDto.getMiddleName(), registrationRqDto.getDateOfBirth(),registrationRqDto.getPhone(), registrationRqDto.getEmail(),
                registrationRqDto.getLogin().toLowerCase(Locale.ROOT), registrationRqDto.getPassword().toLowerCase(Locale.ROOT)));

        tokenData.put("clientType", "user");
        tokenData.put("userID", clientRepository.findAll().size()+1);
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

        RegistrationRsDto registrationRsDto = new RegistrationRsDto();
        registrationRsDto.setVerification(ver);
        registrationRsDto.setToken(token);
        return registrationRsDto;
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

    public boolean passwordMath(Integer id, String password) {
        Optional<Client> candidate = Optional.ofNullable(clientRepository.getById(id));
        if (candidate.isEmpty()) {
            return false;
        }
        Client client = candidate.get();
        byte[] salt = decodeB64(client.getPassword());
        byte[] hashedPassword = decodeB64(client.getPassword());
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
        md.update(salt);
        byte[] toCheck = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return Arrays.equals(toCheck, hashedPassword);
    }

    private String encodeB64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decodeB64(String data) {
        return Base64.getDecoder().decode(data);
    }
}

