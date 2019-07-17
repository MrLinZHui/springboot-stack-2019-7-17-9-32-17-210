package com.tw.apistackbase;

import javax.persistence.*;

@Entity
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int caseId;

    private long caseTime;

    private String caseName;

    public Case() {
    }

    public Case(String caseName, long caseTime) {
        this.caseName = caseName;
        this.caseTime = caseTime;
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
    @Column(name = "caseTime",nullable = false)
    public long getCaseTime() {
        return caseTime;
    }

    public void setCaseTime(long caseTime) {
        this.caseTime = caseTime;
    }
    @Column(name = "caseName",length = 255,nullable = false)
    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }
}
