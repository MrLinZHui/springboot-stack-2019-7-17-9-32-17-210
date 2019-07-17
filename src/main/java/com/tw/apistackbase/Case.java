package com.tw.apistackbase;

import javax.persistence.*;

@Entity
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int caseId;
    @Column(name = "caseTime",nullable = false)
    private long caseTime;
    @Column(name = "caseName",length = 255,nullable = false)
    private String caseName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "caseSInfoId",referencedColumnName = "caseSpecificInformationId")
    private CaseSpecificInformation caseSpecificInformation;
    public Case() {
    }

    public Case(String caseName, long caseTime) {
        this.caseName = caseName;
        this.caseTime = caseTime;
    }

    public Case(String caseName) {
        this.caseName = caseName;
    }

    public Case(long caseTime) {
        this.caseTime = caseTime;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public long getCaseTime() {
        return caseTime;
    }

    public void setCaseTime(long caseTime) {
        this.caseTime = caseTime;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public CaseSpecificInformation getCaseSpecificInformation() {
        return caseSpecificInformation;
    }

    public void setCaseSpecificInformation(CaseSpecificInformation caseSpecificInformation) {
        this.caseSpecificInformation = caseSpecificInformation;
    }

    public Case(long caseTime, String caseName, CaseSpecificInformation caseSpecificInformation) {
        this.caseTime = caseTime;
        this.caseName = caseName;
        this.caseSpecificInformation = caseSpecificInformation;
    }
}
