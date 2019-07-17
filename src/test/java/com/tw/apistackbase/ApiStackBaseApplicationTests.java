package com.tw.apistackbase;

import com.tw.apistackbase.repository.CaseRepository;
import com.tw.apistackbase.repository.CaseSpecificInformationRepository;
import com.tw.apistackbase.repository.ProcuratorateRepository;
import com.tw.apistackbase.repository.ProsecutorRepository;
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
import org.springframework.transaction.annotation.Transactional;

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

	@Autowired
	ProsecutorRepository prosecutorRepository;
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
		Procuratorate procuratorate = new Procuratorate("DongGuang");
		CaseSpecificInformation caseSpecificInformation =
				new CaseSpecificInformation("subjec1","object1");

		Case case1 = new Case(1970010111,"case1",caseSpecificInformation,procuratorate);
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
	@Transactional
	public void shoule_return_all_casse_when_check_all_case(){
		//given
		Procuratorate procuratorate = new Procuratorate("DongGuang1");
		Procuratorate procuratorate1 = new Procuratorate("shanghai");
		procuratorateRepository.saveAll(Arrays.asList(procuratorate,procuratorate1));
		List<Procuratorate> procuratorates = procuratorateRepository.findAll();
		CaseSpecificInformation caseSpecificInformation =
				new CaseSpecificInformation("subjec1","object1");
		CaseSpecificInformation caseSpecificInformation1 =
				new CaseSpecificInformation("subjec2","object2");
		List<CaseSpecificInformation> caseSpecificInformations =
		caseSpecificInformationRepository.saveAll(Arrays.asList(caseSpecificInformation,caseSpecificInformation1));
		Case case1 = new Case(1970010111,"case1",caseSpecificInformations.get(0),procuratorates.get(0));
		Case case3 = new Case(1980010111,"case2",caseSpecificInformations.get(1),procuratorates.get(1));
		caseRepository.saveAndFlush(case1);
		caseRepository.saveAndFlush(case3);
		//when
		List<Case> caseList = caseRepository.findByOrderByCaseTimeDesc();
		//then
		Assertions.assertEquals(1980010111,caseList.get(0).getCaseTime());
		Assertions.assertEquals(1970010111,caseList.get(1).getCaseTime());
	}
	@Test
	@Transactional
	public void shoule_return_all_casse_when_give_a_name(){
		//given
		Procuratorate procuratorate = new Procuratorate("DongGuang2");
		CaseSpecificInformation caseSpecificInformation =
				new CaseSpecificInformation("subjec1","object1");
		Case case1 = new Case(1970010111,"case1",caseSpecificInformation,procuratorate);
		Case case2 = new Case(1990010111,"case1",caseSpecificInformation,procuratorate);
		Case case3 = new Case(1980010111,"case2",caseSpecificInformation,procuratorate);
		caseRepository.saveAll(asList(case1,case2,case3));
		//when
		List<Case> caseList = caseRepository.findCasesByCaseName("case1");
		//then
		Assertions.assertEquals(2,caseList.size());
	}
	@Test
	@Transactional
	public void shoule_delete_casse_when_give_a_id(){
		//given
		Procuratorate procuratorate = new Procuratorate("DongGuang3");
		Procuratorate procuratorate1 = new Procuratorate("shanghai1");
		CaseSpecificInformation caseSpecificInformation =
				new CaseSpecificInformation("subjec1","object1");
		CaseSpecificInformation caseSpecificInformation1 =
				new CaseSpecificInformation("subject2","object2");
		CaseSpecificInformation caseSpecificInformation2 =
				new CaseSpecificInformation("subject3","subject3");
		Case case1 = new Case(1970010111,"case1",caseSpecificInformation,procuratorate);
		Case case2 = new Case(1990010111,"case2",caseSpecificInformation1,procuratorate);
		Case case3 = new Case(1980010111,"case3",caseSpecificInformation2,procuratorate1);
		caseRepository.saveAll(asList(case1,case2,case3));
		//when
		List<Case> caseList =caseRepository.findAll();
		caseRepository.deleteById(caseList.get(2).getCaseId());
		caseList =caseRepository.findAll();
				//then
		Assertions.assertEquals(2,caseList.size());
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
	@Transactional
	public void should_return_a_procuratorate_when_give_a_id(){
		//given
		procuratorateRepository.deleteAll();
		Procuratorate procuratorate = new Procuratorate("DongGuang4");
		procuratorateRepository.save(procuratorate);
		//when
		Procuratorate procuratorate1 = procuratorateRepository.findById(1).get();
		// then
		Assertions.assertEquals("DongGuang4",procuratorate1.getProcuratorateName());
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
		Prosecutor procuratorate1 = prosecutorRepository.findById(1).get();
		// then
		Assertions.assertEquals("Tomi",procuratorate1.getProsecutorName());
	}
}
