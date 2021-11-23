package com.example.couponbazar.Model;

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

    public String getBrand() {
        return brand;
    }

    public String getBenefits() {
        return benefits;
    }
}
