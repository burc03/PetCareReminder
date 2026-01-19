package com.example.petcarereminder.model;

public class PetModel {

    private String id;   // Firestore document id
    private String name;
    private String type;
    private int age;

    // 🔴 Firebase için ZORUNLU
    public PetModel() {}

    public PetModel(String id, String name, String type, int age) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
