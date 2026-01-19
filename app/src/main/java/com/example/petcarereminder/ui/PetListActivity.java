package com.example.petcarereminder.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcarereminder.R;
import com.example.petcarereminder.adapter.PetAdapter;
import com.example.petcarereminder.data.PetRepository;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PetListActivity extends AppCompatActivity {

    private PetAdapter petAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        // 🔙 TOOLBAR (GERİ OK)
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        // 🔹 RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerPets);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        petAdapter = new PetAdapter(this, PetRepository.getPets());
        recyclerView.setAdapter(petAdapter);

        // ➕ FAB → AddPetActivity
        FloatingActionButton fabAddPet = findViewById(R.id.fabAddPet);
        fabAddPet.setOnClickListener(v -> {
            Intent intent = new Intent(PetListActivity.this, AddPetActivity.class);
            startActivity(intent);
        });
    }

    // 🔄 Geri gelince listeyi yenile
    @Override
    protected void onResume() {
        super.onResume();
        if (petAdapter != null) {
            petAdapter.notifyDataSetChanged();
        }
    }
}
