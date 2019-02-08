package edu.pitt.is18.jip45.emr;

public class Diagnoses {

    private int diagnosesID;
    private String name;

    public Diagnoses(int diagnosesID, String name) {
        this.diagnosesID = diagnosesID;
        this.name = name;
    }

    public void setDiagnosesID(int diagnosesID) {
        this.diagnosesID = diagnosesID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiagnosesID() {
        return this.diagnosesID;
    }

    public String getName() {
        return this.name;
    }
}
