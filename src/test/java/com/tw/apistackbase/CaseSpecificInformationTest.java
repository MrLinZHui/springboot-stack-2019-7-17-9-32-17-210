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
public class CaseSpecificInformationTest {
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
    @Transactional
    public void shoule_return_casespecificinformation_when_give_a_id(){
        //given
        caseSpecificInformationRepository.deleteAll();
        procuratorateRepository.deleteAll();
        CaseSpecificInformation caseSpecificInformation =
                new CaseSpecificInformation("subjec1","object1");
        CaseSpecificInformation caseSpecificInformation1 =
                new CaseSpecificInformation("subject2","object2");
        CaseSpecificInformation caseSpecificInformation2 =
                new CaseSpecificInformation("subject3","object3");
        caseSpecificInformationRepository.saveAll(asList(caseSpecificInformation,caseSpecificInformation1,caseSpecificInformation2));
        //when
        CaseSpecificInformation caseSpecificInformation4 = caseSpecificInformationRepository.findBysubjectiveRequirement("subject2");
        CaseSpecificInformation caseSpecificInformation3 = caseSpecificInformationRepository.findById(caseSpecificInformation4.getCaseSpecificInformationId()).get();
        //then
        Assertions.assertEquals("subject2",caseSpecificInformation3.getSubjectiveRequirement());
        Assertions.assertEquals("object2",caseSpecificInformation3.getObjectiveRequirement());
    }
}
