package com.caresoft.clinicapp;

import com.caresoft.clinicapp.HIPAACompliantAdmin;
import com.caresoft.clinicapp.HIPAACompliantUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {
    private Integer employeeID;
    private String role;
    private ArrayList<String> securityIncidents;

    public AdminUser(Integer id, String role) {
        super(id);
        this.role = role;
        this.securityIncidents = new ArrayList<>();
    }

    @Override
    public ArrayList<String> reportSecurityIncidents() {
        return this.securityIncidents;
    }

    @Override
    public boolean assignPin(int pin) {
        if (String.format("%d", pin).length() == 6){
            this.setPin(pin);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean accessAuthorized(Integer confirmedAuthID) {
        if (Objects.equals(confirmedAuthID, this.id)) {
            return true;
        } else {
            authIncident();
            return false;
        }
    }

    public void newIncident(String notes) {
        String report = String.format("Datetime Submitted: %s \n, Reported By ID: %s\n Notes: %s\n", new Date(), this.getId(), notes);
        securityIncidents.add(report);
    }

    public void authIncident() {
        String report = String.format("Datetime Submitted: %s \n, ID: %s\n Notes: %s \n", new Date(), this.getId(), "AUTHORIZATION ATTEMPT FAILED FOR THIS USER");
        securityIncidents.add(report);
    }

    private void setPin(int pin) {
        this.pin = pin;
    }
}