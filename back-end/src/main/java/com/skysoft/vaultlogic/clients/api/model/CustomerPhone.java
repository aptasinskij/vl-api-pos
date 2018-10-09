package com.skysoft.vaultlogic.clients.api.model;

public class CustomerPhone {

    public final String phone;

    public CustomerPhone(String phone) {
        this.phone = phone;
    }

    public static CustomerPhone of(String phone) {
        return new CustomerPhone(phone);
    }

}