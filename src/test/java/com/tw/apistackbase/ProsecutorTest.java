package com.tw.apistackbase;
import com.tw.apistackbase.repository.CaseRepository;
import com.tw.apistackbase.repository.CaseSpecificInformationRepository;
import com.tw.apistackbase.repository.ProcuratorateRepository;
import com.tw.apistackbase.repository.ProsecutorRepository;
import org.hibernate.annotations.Synchronize;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProsecutorTest {
    @Autowired
    CaseRepository caseRepository;

    @Autowired
    CaseSpecificInformationRepository caseSpecificInformationRepository;

    @Autowired
    ProcuratorateRepository procuratorateRepository;

    @Autowired
    ProsecutorRepository prosecutorRepository;
    @Before
    public void CleanEach(){
        caseRepository.deleteAll();
        caseSpecificInformationRepository.deleteAll();
        procuratorateRepository.deleteAll();
        prosecutorRepository.deleteAll();
    }
    @Test
    public void should_throws_Exception_when_ProcuratorName_is_null(){
        //given
        Prosecutor prosecutor = new Prosecutor(null);
        //when+then
        Assertions.assertThrows(Exception.class,()->prosecutorRepository.save(prosecutor));
    }
    @Test
    public void should_return_a_procurator_when_give_a_id(){
        //given
        Prosecutor prosecutor = new Prosecutor("Tomi");
        prosecutorRepository.save(prosecutor);
        //when
        Prosecutor procuratorate2 = prosecutorRepository.findByProsecutorName("Tomi");
        Prosecutor procuratorate1 = prosecutorRepository.findById(procuratorate2.getProsecutorId()).get();
        // then
        Assertions.assertEquals("Tomi",procuratorate1.getProsecutorName());
    }
    @Test
    public void should_return_procurators_when_get_All(){
        //given
        Prosecutor prosecutor = new Prosecutor("Tomi");
        Prosecutor prosecutor1 = new Prosecutor("Lance");
        Procuratorate procuratorate = new Procuratorate("ZhuHai",Arrays.asList(prosecutor,prosecutor1));

        procuratorateRepository.save(procuratorate);
        //when
        Procuratorate procuratorate1 = procuratorateRepository.findAll().get(0);
        // then
        Assertions.assertEquals(2,procuratorate1.getProsecutors().size());
    }
}
