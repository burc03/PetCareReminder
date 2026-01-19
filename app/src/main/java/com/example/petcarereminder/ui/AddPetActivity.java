package com.example.petcarereminder.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petcarereminder.R;
import com.example.petcarereminder.data.local.AppDatabase;
import com.example.petcarereminder.data.local.PetEntity;
import com.google.android.material.appbar.MaterialToolbar;

public class AddPetActivity extends AppCompatActivity {

    private int petId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        // 🔙 Toolbar
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        EditText etName = findViewById(R.id.etPetName);
        EditText etType = findViewById(R.id.etPetType);
        EditText etAge = findViewById(R.id.etPetAge);
        Button btnSave = findViewById(R.id.btnSavePet);

        petId = getIntent().getIntExtra("pet_id", -1);

        // ✏️ Edit mode → veriyi doldur
        if (petId != -1) {
            PetEntity pet =
                    AppDatabase.getInstance(this)
                            .petDao()
                            .getById(petId);

            if (pet != null) {
                etName.setText(pet.name);
                etType.setText(pet.type);
                etAge.setText(String.valueOf(pet.age));
                btnSave.setText("Güncelle");
            }
        }

        // 💾 Kaydet / Güncelle
        btnSave.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String type = etType.getText().toString().trim();
            String ageText = etAge.getText().toString().trim();

            if (name.isEmpty() || type.isEmpty() || ageText.isEmpty()) {
                Toast.makeText(this, "Tüm alanları doldurun", Toast.LENGTH_SHORT).show();
                return;
            }

            int age;
            try {
                age = Integer.parseInt(ageText);
            } catch (Exception e) {
                Toast.makeText(this, "Yaş sayı olmalı", Toast.LENGTH_SHORT).show();
                return;
            }

            PetEntity pet = new PetEntity();
            pet.name = name;
            pet.type = type;
            pet.age = age;

            if (petId != -1) {
                pet.id = petId;
                AppDatabase.getInstance(this).petDao().update(pet);
                Toast.makeText(this, "Güncellendi", Toast.LENGTH_SHORT).show();
            } else {
                AppDatabase.getInstance(this).petDao().insert(pet);
                Toast.makeText(this, "Eklendi", Toast.LENGTH_SHORT).show();
            }

            finish();
        });
    }
}
