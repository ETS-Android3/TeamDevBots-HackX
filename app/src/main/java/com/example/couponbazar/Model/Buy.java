package com.example.couponbazar.Model;

public class Buy {
    String name,benefits,price,brand,phoneNo,code;

    public String getCode() {
        return code;
    }

    public Buy() {
    }

    public Buy(String name, String benefits, String price, String brand, String phoneNo,String code) {
        this.name = name;
        this.benefits = benefits;
        this.price = price;
        this.brand = brand;
        this.phoneNo = phoneNo;
        this.code = code;
    }

    public String getName() {
        return name;
    }



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


    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
