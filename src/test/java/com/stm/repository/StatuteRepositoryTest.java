package com.stm.repository;
import com.stm.Entity.Statute;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StatuteRepositoryTest {
    @Autowired
    @Mock
    StatuteRepository statuteRepository;


    @Test
    public void shouldGetStatuteById(){
//        Statute statute = new Statute("Новая");
//        statuteRepository.save(statute);
//        statute = statuteRepository.getStatuteById(1);
//        assertEquals("Новая", statute.getStatusname());
    }

}