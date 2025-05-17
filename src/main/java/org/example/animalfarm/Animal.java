package org.example.animalfarm;

import java.util.Date;

public class Animal {
    private String id;
    private String name;
    private String gender;
    private String age;
    private double lastWeight;
    private String status;
    private String breed;
    private String type;
    private String tag;
    private String location;
    private String color;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLastmeasured() {
        return lastmeasured;
    }

    public void setLastmeasured(String lastmeasured) {
        this.lastmeasured = lastmeasured;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPaternity() {
        return paternity;
    }

    public void setPaternity(String paternity) {
        this.paternity = paternity;
    }

    public String getMaternity() {
        return maternity;
    }

    public void setMaternity(String maternity) {
        this.maternity = maternity;
    }

    public String getOffspring() {
        return offspring;
    }

    public void setOffspring(String offspring) {
        this.offspring = offspring;
    }

    private double height;
    private String lastmeasured;
    private String birthdate;
    private String paternity;
    private String maternity ;
    private String offspring;


    public Animal(String name, String id, String gender, String age, double lastWeight, String status, String breed, String type, String tag, String location, String color, double height, String lastmeasured, String birthdate, String paternity, String maternity, String offspring) {
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.lastWeight = lastWeight;
        this.status = status;
        this.breed = breed;
        this.type = type;
        this.tag = tag;
        this.location = location;
        this.color = color;
        this.height = height;
        this.lastmeasured = lastmeasured;
        this.birthdate = birthdate;
        this.paternity = paternity;
        this.maternity = maternity;
        this.offspring = offspring;
    }


    public String getId() { return id; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public String getAge() { return age; }
    public double getLastWeight() { return lastWeight; }
    public String getStatus() { return status; }
    public String getBreed() { return breed; }
}
