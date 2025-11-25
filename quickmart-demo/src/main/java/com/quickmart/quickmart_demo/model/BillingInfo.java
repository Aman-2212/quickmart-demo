package com.quickmart.quickmart_demo.model;

public class BillingInfo {
    private String name;
    private String address;
    private String city;
    private String postalCode;
    private String phone;

    public BillingInfo() {}

    // getters / setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
