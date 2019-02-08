package edu.pitt.is18.jip45.emr;

import javax.swing.JOptionPane;

public class EMR {

    public static void main(String[] args) {
        Patient patient = new Patient("John", "Doe", "111-11-1111", 'm', 170, 70);
        String symptom = JOptionPane.showInputDialog("Please enter your symptom:");
        patient.setSymptom(symptom);
        Doctor doctor = new Doctor("Jane", "Smith", "222-22-2222");
        doctor.prescribe(patient);

        String patientProfile = "Name: " + patient.getLastName() + ", " + patient.getFirstName() + "\n";
        patientProfile = patientProfile + "SSN: " + patient.getSsn() + "\n";
        patientProfile = patientProfile + "BMI: " + patient.calculateBMI() + "\n";

        if (patient.getDiagnoses() != null) {
            patientProfile = patientProfile + "Diagnoses: " + patient.getDiagnoses().getName() + "\n";
        } else {
            patientProfile = patientProfile + "Diagnoses: none\n";
        }

        if (patient.getMedication() != null) {
            patientProfile = patientProfile + "Medication: " + patient.getMedication().getName() + "\n";
        } else {
            patientProfile = patientProfile + "Medication: none\n";
        }
        JOptionPane.showMessageDialog(null, patientProfile);

    }
}
