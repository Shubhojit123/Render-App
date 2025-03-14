package com.shubho.cupon.entity;

import jakarta.persistence.*;

@Entity
public class Cupon {
    public Cupon() {
    }
    public Cupon(Integer id, String cupons) {
        this.id = id;
        this.cupons = cupons;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCupons() {
        return cupons;
    }

    public void setCupons(String cupons) {
        this.cupons = cupons;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true,nullable = false)
    private String cupons;
}
