package com.tw.apistackbase;

import com.tw.apistackbase.repository.CaseRepository;
import com.tw.apistackbase.repository.CaseSpecificInformationRepository;
import com.tw.apistackbase.repository.ProcuratorateRepository;
import org.h2.jdbc.JdbcSQLException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiStackBaseApplicationTests {

	@Autowired
	CaseRepository caseRepository;

	@Autowired
	CaseSpecificInformationRepository caseSpecificInformationRepository;

	@Autowired
	ProcuratorateRepository procuratorateRepository;
	//todo
	@Test
	public void shoule_save_a_case_when_give_a_case(){
		//given
		Case case1 = new Case(null,1970010111);
		//when
		//when+then
		Assertions.assertThrows(Exception.class,()->caseRepository.save(case1));

	}
	@Test
	public void shoule_return_a_case_when_give_a_id(){
		//given
		CaseSpecificInformation caseSpecificInformation =
				new CaseSpecificInformation("subjec1","object1");

		Case case1 = new Case(1970010111,"case1",caseSpecificInformation);
		caseRepository.saveAndFlush(case1);
		//when
		Case case2 = caseRepository.findById(1).get();
		//then
		Assertions.assertEquals(1,case2.getCaseId());
		Assertions.assertEquals("case1",case2.getCaseName());
		Assertions.assertEquals(1970010111,case2.getCaseTime());
		Assertions.assertEquals("subjec1",case2.getCaseSpecificInformation().getSubjectiveRequirement());
		Assertions.assertEquals("object1",case2.getCaseSpecificInformation().getObjectiveRequirement());
		//Assertions.assertThrows(Exception.class,()->caseRepository.findAll());
	}
	@Test
	public void shoule_return_all_casse_when_check_all_case(){
		//given
		CaseSpecificInformation caseSpecificInformation =
				new CaseSpecificInformation("subjec1","object1");
		CaseSpecificInformation caseSpecificInformation1 =
				new CaseSpecificInformation("subjec2","object2");
		Case case1 = new Case(1970010111,"case1",caseSpecificInformation);
		Case case3 = new Case(1980010111,"case2",caseSpecificInformation1);
		caseRepository.saveAndFlush(case1);
		caseRepository.saveAndFlush(case3);
		//when
		List<Case> caseList = caseRepository.findByOrderByCaseTimeDesc();
		//then
		Assertions.assertEquals(1980010111,caseList.get(0).getCaseTime());
		Assertions.assertEquals(1970010111,caseList.get(1).getCaseTime());
	}
	@Test
	public void shoule_return_all_casse_when_give_a_name(){
		//given
		CaseSpecificInformation caseSpecificInformation =
				new CaseSpecificInformation("subjec1","object1");
		Case case1 = new Case(1970010111,"case1",caseSpecificInformation);
		Case case2 = new Case(1990010111,"case1",caseSpecificInformation);
		Case case3 = new Case(1980010111,"case2",caseSpecificInformation);
		caseRepository.saveAll(asList(case1,case2,case3));
		//when
		List<Case> caseList = caseRepository.findCasesByCaseName("case1");
		//then
		Assertions.assertEquals(2,caseList.size());
	}
	@Test
	public void shoule_delete_casse_when_give_a_id(){
		//given
		Case case1 = new Case("case1",1970010111);
		Case case2 = new Case("case2",1990010111);
		Case case3 = new Case("case3",1980010111);
		caseRepository.saveAll(asList(case1,case2,case3));
		//when
		caseRepository.deleteById(3);
		List<Case> caseList =caseRepository.findAll();
				//then
		Assertions.assertEquals(2,caseList.size());
		Assertions.assertEquals("case1",caseList.get(0).getCaseName());
		Assertions.assertEquals("case2",caseList.get(1).getCaseName());
	}
	@Test
	public void shoule_return_casespecificinformation_when_give_a_id(){
		//given
		CaseSpecificInformation caseSpecificInformation =
				new CaseSpecificInformation("subjec1","object1");
		CaseSpecificInformation caseSpecificInformation1 =
				new CaseSpecificInformation("subject2","object2");
		CaseSpecificInformation caseSpecificInformation2 =
				new CaseSpecificInformation("subject3","");
		caseSpecificInformationRepository.saveAll(asList(caseSpecificInformation,caseSpecificInformation1,caseSpecificInformation2));
		//when
		CaseSpecificInformation caseSpecificInformation3 = caseSpecificInformationRepository.findById(2).get();
				//then
		Assertions.assertEquals("subject2",caseSpecificInformation3.getSubjectiveRequirement());
		Assertions.assertEquals("object2",caseSpecificInformation3.getObjectiveRequirement());
	}
	//todo
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
		//procuratorateRepository.save(procuratorate);
		//when+then
		Assertions.assertThrows(Exception.class,()->procuratorateRepository.save(procuratorate));
	}
	@Test
	public void contextLoads() {
		
	}

}
