package com.tw.apistackbase;

import javax.persistence.*;

@Entity
public class CaseSpecificInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int caseSpecificInformationId;
    @Column(nullable = false)
    private String subjectiveRequirement;
    @Column(nullable = false)
    private String objectiveRequirement;

    public CaseSpecificInformation() {
    }

    public CaseSpecificInformation(String subjectiveRequirement, String objectiveRequirement) {
        this.subjectiveRequirement = subjectiveRequirement;
        this.objectiveRequirement = objectiveRequirement;
    }

    public int getCaseSpecificInformationId() {
        return caseSpecificInformationId;
    }

    public void setCaseSpecificInformationId(int caseSpecificInformationId) {
        this.caseSpecificInformationId = caseSpecificInformationId;
    }

    public String getSubjectiveRequirement() {
        return subjectiveRequirement;
    }

    public void setSubjectiveRequirement(String subjectiveRequirement) {
        this.subjectiveRequirement = subjectiveRequirement;
    }

    public String getObjectiveRequirement() {
        return objectiveRequirement;
    }

    public void setObjectiveRequirement(String objectiveRequirement) {
        this.objectiveRequirement = objectiveRequirement;
    }
}
