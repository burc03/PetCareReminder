package com.example.petcarereminder.data.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pets")
public class PetEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String type;
    public int age;
}
