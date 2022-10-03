package com.caresoft.clinicapp;

import com.caresoft.clinicapp.HIPAACompliantUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Physician extends User implements HIPAACompliantUser {
    private int id;
    private ArrayList<String> patientNotes;

    public Physician(Integer id) {
        super(id);
        this.patientNotes = new ArrayList<>();
    }

    @Override
    public boolean assignPin(int pin) {
        if (String.format("%d", pin).length() == 4){
            this.setPin(pin);
            return true;
        } else {
            return false;
        }
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public boolean accessAuthorized(Integer confirmedAuthID) {
        return Objects.equals(confirmedAuthID, this.getId());
    }

    public void newPatientNotes(String notes, String patientName, Date date) {
        String report = String.format("Datetime Submitted: %s \n", date);
        report += String.format("Reported By ID: %s \n", this.getId());
        report += String.format("Patient Name: %s \n", patientName);
        report += String.format("Notes: %s \n", notes);
        this.getPatientNotes().add(report);
    }

    ArrayList<String> getPatientNotes() {
        return this.patientNotes;
    }
}