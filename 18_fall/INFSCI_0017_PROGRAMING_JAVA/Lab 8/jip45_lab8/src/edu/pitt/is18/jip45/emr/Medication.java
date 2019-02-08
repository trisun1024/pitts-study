package edu.pitt.is18.jip45.emr;

public class Medication {

    private int medicationID;
    private String name;

    public Medication(int medicationID, String name) {
        this.medicationID = medicationID;
        this.name = name;
    }

    public void setMedicationID(int medicationID) {
        this.medicationID = medicationID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMedicationID() {
        return this.medicationID;
    }

    public String getName() {
        return this.name;
    }
}
