package org.example.animalfarm;

public class Animal {
    private String id;
    private String name;
    private String gender;
    private String age;
    private double lastWeight;
    private String status;
    private String breed;



    public Animal(String id, String name, String gender, String age, double lastWeight, String status, String breed) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.lastWeight = lastWeight;
        this.status = status;
        this.breed = breed;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public String getAge() { return age; }
    public double getLastWeight() { return lastWeight; }
    public String getStatus() { return status; }
    public String getBreed() { return breed; }
}
