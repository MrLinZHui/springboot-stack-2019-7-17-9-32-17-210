package com.tw.apistackbase;

import javax.persistence.*;

@Entity
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int caseId;

    private long castTime;

    private String caseName;

    public Case() {
    }

    public Case(String caseName, long castTime) {
        this.caseName = caseName;
        this.castTime = castTime;
    }

    public Case(String caseName) {
        this.caseName = caseName;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }
    @Column(name = "castTime",nullable = false)
    public long getCastTime() {
        return castTime;
    }

    public void setCastTime(long castTime) {
        this.castTime = castTime;
    }
    @Column(name = "caseName",length = 255,nullable = false)
    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }
}
