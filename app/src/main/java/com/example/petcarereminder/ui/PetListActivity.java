package com.example.petcarereminder.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcarereminder.R;
import com.example.petcarereminder.adapter.PetAdapter;
import com.example.petcarereminder.data.local.AppDatabase;
import com.example.petcarereminder.data.local.PetEntity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PetListActivity extends AppCompatActivity {

    private PetAdapter petAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        // 🔙 Toolbar
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        RecyclerView recyclerView = findViewById(R.id.recyclerPets);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fabAddPet = findViewById(R.id.fabAddPet);
        fabAddPet.setOnClickListener(v ->
                startActivity(new Intent(this, AddPetActivity.class))
        );

        loadPets(recyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.recyclerPets);
        loadPets(recyclerView);
    }

    private void loadPets(RecyclerView recyclerView) {
        List<PetEntity> pets =
                AppDatabase.getInstance(this)
                        .petDao()
                        .getAll();

        petAdapter = new PetAdapter(this, pets);
        recyclerView.setAdapter(petAdapter);
    }
}
