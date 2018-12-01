package edu.pitt.is18.jip45.task1;

public class Cylinder extends Circle {

    private double height = 1.0;

    public Cylinder() {

    }

    public Cylinder(double radius, double height) {
        super(radius);
        this.height = height;
    }

    public Cylinder(double radius, double height, String color) {
        super(radius, color);
        this.height = height;
    }

    public double getHeight(){
        return this.height;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public double getVolume(){
        double volume = Math.PI * this.getRadius() * this.getRadius() * this.getHeight();
        return volume;
    }
}
