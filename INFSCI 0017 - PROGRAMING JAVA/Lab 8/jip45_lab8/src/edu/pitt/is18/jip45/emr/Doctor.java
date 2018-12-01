package edu.pitt.is18.jip45.emr;

public class Doctor {

    private String employeeID;
    private String firstName;
    private String lastName;
    private String ssn;
    private String specialization;

    public Doctor(String firstName, String lastName, String ssn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.employeeID = firstName.substring(0, 1) + lastName.substring(0, 1) + ssn.replaceAll("-", "");
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getSsn() {
        return this.ssn;
    }

    public String getSpecialization() {
        return this.specialization;
    }

    public void prescribe(Patient patient) {
        if (patient.getSymptom().equalsIgnoreCase("Headache")) {
            Diagnoses diagnoseOne = new Diagnoses(1, "Dehidration");
            patient.setDiagnoses(diagnoseOne);
            Medication medicationOne = new Medication(1, "Tylenol");
            patient.setMedication(medicationOne);
        } else if (patient.getSymptom().equalsIgnoreCase("Cough")) {
            Diagnoses diagnoseOne = new Diagnoses(2, "Common cold");
            patient.setDiagnoses(diagnoseOne);
            Medication medicationOne = new Medication(2, "Cough drops");
            patient.setMedication(medicationOne);
        } else if (patient.getSymptom().equalsIgnoreCase("Fever")) {
            Diagnoses diagnoseOne = new Diagnoses(3, "Influenza");
            patient.setDiagnoses(diagnoseOne);
            Medication medicationOne = new Medication(3, "Tamiflu");
            patient.setMedication(medicationOne);
        } else {
            patient.setDiagnoses(null);
            patient.setMedication(null);
        }
    }

}
