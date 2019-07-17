package com.tw.apistackbase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_Procuratorate")
public class Procuratorate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int procuratorateId;
    @Column(name = "procuratorateName",length = 50,nullable = false,unique = true)
    private String procuratorateName;

    public Procuratorate() {
    }

    public Procuratorate(String procuratorateName) {
        this.procuratorateName = procuratorateName;
    }

    public String getProcuratorateName() {
        return procuratorateName;
    }

    public void setProcuratorateName(String procuratorateName) {
        this.procuratorateName = procuratorateName;
    }
}
