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

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Procuratorate procuratorate;

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

    public Procuratorate getProcuratorate() {
        return procuratorate;
    }

    public void setProcuratorate(Procuratorate procuratorate) {
        this.procuratorate = procuratorate;
    }

    public Case(long caseTime, String caseName, CaseSpecificInformation caseSpecificInformation, Procuratorate procuratorate) {
        this.caseTime = caseTime;
        this.caseName = caseName;
        this.caseSpecificInformation = caseSpecificInformation;
        this.procuratorate = procuratorate;
    }
}
