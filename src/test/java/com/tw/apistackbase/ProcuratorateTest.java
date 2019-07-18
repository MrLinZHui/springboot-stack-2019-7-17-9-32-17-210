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
public class ProcuratorateTest {
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
    public void should_throws_Exception_when_ProcuratorateName_is_repeat(){
        //given
        Procuratorate procuratorate = new Procuratorate("GuangDong");
        Procuratorate procuratorate1 = new Procuratorate("GuangDong");
        //when+then
        Assertions.assertThrows(Exception.class,()->procuratorateRepository.saveAll(Arrays.asList(procuratorate,procuratorate1)));
    }
    @Test
    public void should_throws_Exception_when_ProcuratorateName_is_null(){
        //given
        Procuratorate procuratorate = new Procuratorate(null);
        //when+then
        Assertions.assertThrows(Exception.class,()->procuratorateRepository.save(procuratorate));
    }
    @Test
    public void should_throws_Exception_when_ProcuratorateName_is_too_long(){
        //given
        Procuratorate procuratorate = new Procuratorate("DongGuangDongGuangDongGuangDongDongGuangDongGuangDongGuangDong");
        //when+then
        Assertions.assertThrows(Exception.class,()->procuratorateRepository.save(procuratorate));
    }
    @Test
    @Transactional
    public void should_return_a_procuratorate_when_give_a_id(){
        //given
        procuratorateRepository.deleteAll();
        Procuratorate procuratorate = new Procuratorate("DongGuang4");
        procuratorateRepository.save(procuratorate);
        //when
        Procuratorate procuratorate2 = procuratorateRepository.findByProcuratorateName("DongGuang4");
        Procuratorate procuratorate1 = procuratorateRepository.findById(procuratorate2.getProcuratorateId()).get();
        // then
        Assertions.assertEquals("DongGuang4",procuratorate1.getProcuratorateName());
    }
}
