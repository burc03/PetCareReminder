package com.example.petcarereminder.data;

import com.example.petcarereminder.model.PetModel;

import java.util.ArrayList;
import java.util.List;

public class PetRepository {

    // 🔹 Geçici bellek listesi (Room’a geçene kadar yeterli)
    private static final List<PetModel> petList = new ArrayList<>();

    // ➕ Hayvan ekle
    public static void addPet(PetModel pet) {
        petList.add(pet);
    }

    // 📋 Tüm hayvanları getir
    public static List<PetModel> getPets() {
        return petList;
    }

    // ✏️ Hayvan güncelle (Edit için)
    public static void updatePet(int index, PetModel updatedPet) {
        if (index >= 0 && index < petList.size()) {
            petList.set(index, updatedPet);
        }
    }

    // 🗑️ Hayvan sil
    public static void removePet(int index) {
        if (index >= 0 && index < petList.size()) {
            petList.remove(index);
        }
    }

    // 🔍 Tek bir hayvanı getir (Edit ekranı için)
    public static PetModel getPet(int index) {
        if (index >= 0 && index < petList.size()) {
            return petList.get(index);
        }
        return null;
    }

    // 🧹 (Opsiyonel) Tüm listeyi temizle
    public static void clear() {
        petList.clear();
    }
}
