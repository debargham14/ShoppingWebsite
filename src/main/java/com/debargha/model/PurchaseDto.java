package com.debargha.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class PurchaseDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Long id;
    private final ApparelDto apparel;
    private final LocalDate createdAt;

    public PurchaseDto(Long id, ApparelDto apparel, LocalDate createdAt) {
        this.id = id;
        this.apparel = apparel;
        this.createdAt = createdAt;
    }

    public PurchaseDto(Purchase purchase)
    {
        this.id = purchase.getId();
        this.apparel = new ApparelDto(purchase.getApparel());
        this.createdAt = purchase.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public ApparelDto getApparel() {
        return apparel;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseDto entity = (PurchaseDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.apparel, entity.apparel) &&
                Objects.equals(this.createdAt, entity.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, apparel, createdAt);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "apparel = " + apparel + ", " +
                "createdAt = " + createdAt + ")";
    }
}
