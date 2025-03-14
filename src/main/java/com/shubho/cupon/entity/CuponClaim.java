package com.shubho.cupon.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CuponClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ipAddress;
    private String sessionId;

    @ManyToOne
    @JoinColumn(name = "coupon_id", nullable = false)
    private Cupon coupon;

    private LocalDateTime dateTime;

    public CuponClaim() {
    }

    public CuponClaim(Integer id, LocalDateTime dateTime, Cupon coupon, String sessionId, String ipAddress) {
        this.id = id;
        this.dateTime = dateTime;
        this.coupon = coupon;
        this.sessionId = sessionId;
        this.ipAddress = ipAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Cupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Cupon coupon) {
        this.coupon = coupon;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setClaimedAt(LocalDateTime now) {
        this.dateTime = now;
    }
}
