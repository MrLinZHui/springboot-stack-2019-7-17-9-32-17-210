package com.tw.apistackbase;

import com.tw.apistackbase.repository.CaseRepository;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiStackBaseApplicationTests {

	@Autowired
	CaseRepository caseRepository;
	@Test
	public void shoule_save_a_case_when_give_a_case(){
		//given
		Case case1 = new Case("",0L);
		//when
		caseRepository.save(case1);
		//when+then
		Assertions.assertThrows(Exception.class,()->caseRepository.findAll());

	}
	@Test
	public void shoule_return_a_case_when_give_a_id(){
		//given
		Case case1 = new Case("case1",1970010111);
		caseRepository.saveAndFlush(case1);
		//when
		Case case2 = caseRepository.findById(1).get();
		//then
		Assertions.assertEquals(1,case2.getCaseId());
		Assertions.assertEquals("case1",case2.getCaseName());
		Assertions.assertEquals(1970010111,case2.getCaseTime());
		//Assertions.assertThrows(Exception.class,()->caseRepository.findAll());
	}
	@Test
	public void shoule_return_all_casse_when_check_all_case(){
		//given
		Case case1 = new Case("case1",1970010111);
		Case case3 = new Case("case2",1980010111);
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
		Case case1 = new Case("case1",1970010111);
		Case case2 = new Case("case1",1990010111);
		Case case3 = new Case("case2",1980010111);
		caseRepository.saveAll(Arrays.asList(case1,case2,case3));
		//when
		List<Case> caseList = caseRepository.findCasesByCaseName("case1");
		//then
		Assertions.assertEquals(2,caseList.size());
	}
	@Test
	public void contextLoads() {
		
	}

}
