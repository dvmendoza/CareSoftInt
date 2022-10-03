package com.caresoft.clinicapp;

public class User {
	
	protected Integer id;
    protected int pin;

    public User(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public int getPin() {
        return this.pin;
    }

}
