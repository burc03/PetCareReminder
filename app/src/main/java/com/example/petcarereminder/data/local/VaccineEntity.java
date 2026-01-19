package com.example.petcarereminder.data.local;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "vaccines",
        foreignKeys = @ForeignKey(
                entity = PetEntity.class,
                parentColumns = "id",
                childColumns = "petId",
                onDelete = ForeignKey.CASCADE
        )
)
public class VaccineEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int petId;        // 🔗 Hangi hayvana ait
    public String title;     // Aşı adı
    public String date;      // Tarih (String yeterli)
    public String note;      // Not
}
