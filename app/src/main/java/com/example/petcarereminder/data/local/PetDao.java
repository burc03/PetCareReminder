package com.example.petcarereminder.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PetDao {

    // ➕ Hayvan ekle
    @Insert
    void insert(PetEntity pet);

    // ✏️ Hayvan güncelle
    @Update
    void update(PetEntity pet);

    // 🗑️ Hayvan sil
    @Delete
    void delete(PetEntity pet);

    // 📋 Tüm hayvanları getir
    @Query("SELECT * FROM pets ORDER BY id DESC")
    List<PetEntity> getAll();

    // 🔍 ID ile tek hayvan getir (Düzenleme için)
    @Query("SELECT * FROM pets WHERE id = :id LIMIT 1")
    PetEntity getById(int id);
}
