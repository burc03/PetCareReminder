package com.example.petcarereminder.model;

public class PetModel {

    private String name;
    private String type;
    private int age;

    public PetModel(String name, String type, int age) {
        this.name = name;
        this.type = type;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }
}
