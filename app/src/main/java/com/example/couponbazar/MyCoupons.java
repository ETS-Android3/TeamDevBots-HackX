package com.example.couponbazar;

public class MyCoupons {
    String code,brand,benefits;

    public MyCoupons() {
    }

    public MyCoupons(String code, String brand, String benefits) {
        this.code = code;
        this.brand = brand;
        this.benefits = benefits;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }
}
