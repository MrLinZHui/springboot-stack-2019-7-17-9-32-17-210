package com.tw.apistackbase.repository;

import com.tw.apistackbase.CaseSpecificInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseSpecificInformationRepository extends JpaRepository<CaseSpecificInformation,Integer> {
    CaseSpecificInformation findBysubjectiveRequirement(String subject2);
    //CaseSpecificInformation findBysubjectiveRequirement(String subject2);
}
