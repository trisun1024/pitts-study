package edu.pitt.is18.jip45.task2;

public abstract class Person {

    private String name;
    private String address = "String";

    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setName(String name){
        this.name = name;
    }
}
