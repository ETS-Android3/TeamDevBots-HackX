package com.example.couponbazar;

public class Buy {
    String name,benefits,price,brand,phoneNo;

    public Buy() {
    }

    public Buy(String name, String benefits, String price, String brand, String phoneNo) {
        this.name = name;
        this.benefits = benefits;
        this.price = price;
        this.brand = brand;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

//    public void setBrand(String brand) {
//        this.brand = brand;
//    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
