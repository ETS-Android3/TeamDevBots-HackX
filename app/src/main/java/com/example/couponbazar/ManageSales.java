package com.example.couponbazar;


public class ManageSales {
     String brand,benefits,code;
     String price;

    public ManageSales() {
    }

    public ManageSales(String brand, String benefits, String code, String price) {
        this.brand = brand;
        this.benefits = benefits;
        this.code = code;
        this.price = price;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
