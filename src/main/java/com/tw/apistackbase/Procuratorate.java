package com.tw.apistackbase;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "T_Procuratorate")
public class Procuratorate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int procuratorateId;
    @Column(name = "procuratorateName",length = 50,nullable = false,unique = true)
    private String procuratorateName;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Prosecutor> prosecutors;
    public Procuratorate() {
    }

    public Procuratorate(String procuratorateName) {
        this.procuratorateName = procuratorateName;
    }

    public Procuratorate(String procuratorateName, List<Prosecutor> prosecutors) {
        this.procuratorateName = procuratorateName;
        this.prosecutors = prosecutors;
    }

    public String getProcuratorateName() {
        return procuratorateName;
    }

    public void setProcuratorateName(String procuratorateName) {
        this.procuratorateName = procuratorateName;
    }

    public List<Prosecutor> getProsecutors() {
        return prosecutors;
    }

    public void setProsecutors(List<Prosecutor> prosecutors) {
        this.prosecutors = prosecutors;
    }
}
