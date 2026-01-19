package com.example.petcarereminder.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VaccineDao {

    // ➕ Aşı ekle
    @Insert
    void insert(VaccineEntity vaccine);

    // 📋 Seçilen hayvana ait aşıları getir
    @Query("SELECT * FROM vaccines WHERE petId = :petId ORDER BY date")
    List<VaccineEntity> getByPetId(int petId);
}
