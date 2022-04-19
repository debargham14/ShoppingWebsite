package com.debargha.model;



import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class ApparelDto implements Serializable {
    

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UUID id;
    private Sex gender;
    private String brandName;
    private String genericName;
    private Double price;
    private Double discountedPrice;
    private Season season;
    private String imageUrl;
    
    public ApparelDto() {

    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public ApparelDto(Apparel apparel) {
        this.id = apparel.getId();
        this.brandName = apparel.getBrandName();
        this.genericName = apparel.getGenericName();
        this.gender = apparel.getGender();
        this.price = apparel.getPrice();
        this.season = apparel.getSeason();
        this.imageUrl = apparel.getImageUrl();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApparelDto entity = (ApparelDto) o;
        return Objects.equals(this.gender, entity.gender) &&
                Objects.equals(this.brandName, entity.brandName) &&
                Objects.equals(this.genericName, entity.genericName) &&
                Objects.equals(this.price, entity.price) &&
                Objects.equals(this.season, entity.season) &&
                Objects.equals(this.imageUrl, entity.imageUrl);
    }
    public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setDiscountedPrice(Double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

    @Override
    public int hashCode() {
        return Objects.hash(gender, brandName, genericName, price, season, imageUrl);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "gender = " + gender + ", " +
                "brandName = " + brandName + ", " +
                "genericName = " + genericName + ", " +
                "price = " + price + ", " +
                "season = " + season + ")";
    }
}
