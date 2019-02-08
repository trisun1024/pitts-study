package edu.pitt.is18.jip45.task2;

public class Staff extends Person {
    private String school;
    private double pay;

    public Staff(String name, String address, double pay) {
        super();
        this.pay = pay;
    }

    public String getSchool() {
        return this.school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public double getPay() {
        return this.pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return this.getName() + "\n" + this.getAddress() + "\nWorking in school: " + this.getSchool() + " and having salary of " + this.getPay();
    }
}
