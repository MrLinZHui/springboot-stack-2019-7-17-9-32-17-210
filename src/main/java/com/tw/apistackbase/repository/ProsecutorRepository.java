package com.tw.apistackbase.repository;

import com.tw.apistackbase.Prosecutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProsecutorRepository extends JpaRepository<Prosecutor,Integer> {
    Prosecutor findByProsecutorName(String prosecutorName);
}
