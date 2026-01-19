package com.example.petcarereminder.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petcarereminder.R;
import com.example.petcarereminder.data.PetRepository;
import com.example.petcarereminder.model.PetModel;
import com.google.android.material.appbar.MaterialToolbar;

public class AddPetActivity extends AppCompatActivity {

    private boolean editMode = false;
    private int editIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        // 🔙 TOOLBAR GERİ
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        EditText etName = findViewById(R.id.etPetName);
        EditText etType = findViewById(R.id.etPetType);
        EditText etAge = findViewById(R.id.etPetAge);
        Button btnSave = findViewById(R.id.btnSavePet);

        // 🔹 Edit mode kontrolü
        editMode = getIntent().getBooleanExtra("edit_mode", false);
        editIndex = getIntent().getIntExtra("pet_index", -1);

        // ✏️ DÜZENLEME MODU
        if (editMode && editIndex != -1) {
            PetModel pet = PetRepository.getPet(editIndex);
            if (pet != null) {
                etName.setText(pet.getName());
                etType.setText(pet.getType());
                etAge.setText(String.valueOf(pet.getAge()));
                btnSave.setText("Güncelle");
            }
        }

        // 💾 KAYDET / GÜNCELLE
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
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Yaş geçerli bir sayı olmalı", Toast.LENGTH_SHORT).show();
                return;
            }

            PetModel pet = new PetModel(name, type, age);

            if (editMode && editIndex != -1) {
                PetRepository.updatePet(editIndex, pet);
                Toast.makeText(this, "Hayvan güncellendi", Toast.LENGTH_SHORT).show();
            } else {
                PetRepository.addPet(pet);
                Toast.makeText(this, "Hayvan eklendi", Toast.LENGTH_SHORT).show();
            }

            finish();
        });
    }
}
