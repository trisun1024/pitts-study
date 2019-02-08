package edu.pitt.is18.jip45.emr;

public class Patient {

    private String patientID;
    private String firstName;
    private String lastName;
    private String ssn;
    private char gender;
    private Diagnoses diagnoses;
    private Medication medication;
    private String symptom;
    private double weight;
    private double height;

    public Patient(String firstName, String lastName, String ssn, char gender, double weight, double height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.patientID = firstName.substring(0, 1) + lastName.substring(0, 1) + ssn.replaceAll("-", "");
    }

    // setter and getter for basic elements
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
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

    public char getGender() {
        return this.gender;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getHeight() {
        return this.height;
    }

    // setter and getter for others
    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getSymptom() {
        return this.symptom;
    }

    public void setDiagnoses(Diagnoses diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Diagnoses getDiagnoses() {
        return this.diagnoses;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Medication getMedication() {
        return this.medication;
    }

    public double calculateBMI() {
        // BMI calculation in Pounds and Inches
        final int PARAMETER_OF_BMI_IN_POUNDS_AND_INCHES = 703;
        double bmi = weight / (height * height) * PARAMETER_OF_BMI_IN_POUNDS_AND_INCHES;
        return bmi;
    }
}
