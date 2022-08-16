package com.cdweb.api.web.input;

import java.util.ArrayList;
import java.util.List;

public class OrderedInput {
    private String email;
    private String name;
    private String phone;
    private String address;
    private List<Long> carts = new ArrayList<>();

    public List<Long> getCarts() {
        return carts;
    }

    public void setCarts(List<Long> carts) {
        this.carts = carts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
