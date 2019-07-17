package com.tw.apistackbase;

import javax.persistence.*;

@Entity
public class Prosecutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int prosecutorId;
    @Column(nullable = false,length = 255)
    private String prosecutorName;

    public Prosecutor() {
    }

    public Prosecutor(String prosecutorName) {
        this.prosecutorName = prosecutorName;
    }

    public int getProsecutorId() {
        return prosecutorId;
    }

    public void setProsecutorId(int prosecutorId) {
        this.prosecutorId = prosecutorId;
    }

    public String getProsecutorName() {
        return prosecutorName;
    }

    public void setProsecutorName(String prosecutorName) {
        this.prosecutorName = prosecutorName;
    }
}
