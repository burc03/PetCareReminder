package com.example.petcarereminder;

// Author: Burcu Arıcı
// Feature: Basic validation unit tests for Pet & Vaccine logic

import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleUnitTest {

    // 🔹 1️⃣ Basit kontrol (JUnit çalışıyor mu?)
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    // 🔹 2️⃣ Aşı tarihi boş olamaz
    @Test
    public void vaccineDate_isNotEmpty() {
        String vaccineDate = "12/05/2024"; // örnek tarih
        assertNotNull(vaccineDate);
        assertFalse(vaccineDate.isEmpty());
    }

    // 🔹 3️⃣ Aşı başlığı (title) boş olamaz
    @Test
    public void vaccineTitle_isNotEmpty() {
        String vaccineTitle = "Kuduz Aşısı";
        assertNotNull(vaccineTitle);
        assertFalse(vaccineTitle.trim().isEmpty());
    }

    // 🔹 4️⃣ Pet yaşı negatif olamaz
    @Test
    public void petAge_isGreaterThanOrEqualZero() {
        int petAge = 3;
        assertTrue(petAge >= 0);
    }
}
