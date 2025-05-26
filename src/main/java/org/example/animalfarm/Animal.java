package org.example.animalfarm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Animal {
    private String id;
    private String name;
    private String gender;
    private String age;
    private Double lastWeight;
    private String status;
    private String breed;
    private String type;
    private String tag;
    private String location;
    private String color;
    private Double height;
    private String lastmeasured;
    private String birthday;
    private String paternity;
    private String maternity;
    private String offspring;

    @JsonCreator
    public Animal(
            @JsonProperty("name") String name,
            @JsonProperty("id") String id,
            @JsonProperty("gender") String gender,
            @JsonProperty("age") String age,
            @JsonProperty("lastWeight") Double lastWeight,
            @JsonProperty("status") String status,
            @JsonProperty("breed") String breed,
            @JsonProperty("type") String type,
            @JsonProperty("tag") String tag,
            @JsonProperty("location") String location,
            @JsonProperty("color") String color,
            @JsonProperty("height") Double height,
            @JsonProperty("lastmeasured") String lastmeasured,
            @JsonProperty("birthday") String birthday,
            @JsonProperty("paternity") String paternity,
            @JsonProperty("maternity") String maternity,
            @JsonProperty("offspring") String offspring) {
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
        this.birthday = birthday;
        this.paternity = paternity;
        this.maternity = maternity;
        this.offspring = offspring;
    }

    // Getters
    public String getName() { return name; }
    public String getId() { return id; }
    public String getGender() { return gender; }
    public String getAge() { return age; }
    public Double getLastWeight() { return lastWeight; }
    public String getStatus() { return status; }
    public String getBreed() { return breed; }
    public String getType() { return type; }
    public String getTag() { return tag; }
    public String getLocation() { return location; }
    public String getColor() { return color; }
    public Double getHeight() { return height; }
    public String getLastmeasured() { return lastmeasured; }
    public String getBirthday() { return birthday; }
    public String getPaternity() { return paternity; }
    public String getMaternity() { return maternity; }
    public String getOffspring() { return offspring; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setId(String id) { this.id = id; }
    public void setGender(String gender) { this.gender = gender; }
    public void setAge(String age) { this.age = age; }
    public void setLastWeight(Double lastWeight) { this.lastWeight = lastWeight; }
    public void setStatus(String status) { this.status = status; }
    public void setBreed(String breed) { this.breed = breed; }
    public void setType(String type) { this.type = type; }
    public void setTag(String tag) { this.tag = tag; }
    public void setLocation(String location) { this.location = location; }
    public void setColor(String color) { this.color = color; }
    public void setHeight(Double height) { this.height = height; }
    public void setLastmeasured(String lastmeasured) { this.lastmeasured = lastmeasured; }
    public void setBirthday(String birthday) { this.birthday = birthday; }
    public void setPaternity(String paternity) { this.paternity = paternity; }
    public void setMaternity(String maternity) { this.maternity = maternity; }
    public void setOffspring(String offspring) { this.offspring = offspring; }
}
