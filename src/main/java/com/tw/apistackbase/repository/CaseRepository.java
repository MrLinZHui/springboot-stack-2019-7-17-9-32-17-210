package com.tw.apistackbase.repository;

import com.tw.apistackbase.Case;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseRepository extends JpaRepository<Case,Integer> {
    List<Case> findByOrderByCaseTimeDesc();
}
