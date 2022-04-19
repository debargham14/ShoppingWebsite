package com.debargha.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Apparel apparel;

    @Column(nullable = false)
    private Double percentage;

    @Column(nullable = false)
    private LocalDateTime validFrom;

    @Column(nullable = false)
    private LocalDateTime validUpto;

    public Deal() {
    }

    public Deal(Apparel apparel, double percentage, LocalDateTime validFrom, LocalDateTime validUpto) {
        this.apparel = apparel;
        this.percentage = percentage;
        this.validFrom = validFrom;
        this.validUpto = validUpto;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidUpto() {
        return validUpto;
    }

    public void setValidUpto(LocalDateTime validUpto) {
        this.validUpto = validUpto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Apparel getApparel() {
        return apparel;
    }

    public void setApparel(Apparel apparel) {
        this.apparel = apparel;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
