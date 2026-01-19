package com.example.petcarereminder.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcarereminder.R;
import com.example.petcarereminder.adapter.PetAdapter;
import com.example.petcarereminder.data.local.AppDatabase;
import com.example.petcarereminder.data.local.PetEntity;
import com.example.petcarereminder.ui.vaccine.VaccineListFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PetListActivity extends AppCompatActivity {

    // Author: Burcu Arıcı
    private PetAdapter petAdapter;
    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        // 🔙 Toolbar
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        // 🔹 Fragment Container (BAŞTA GİZLİ)
        fragmentContainer = findViewById(R.id.fragmentContainer);
        if (fragmentContainer != null) {
            fragmentContainer.setVisibility(View.GONE);
        }

        // 🔹 RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerPets);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ➕ Hayvan Ekle
        FloatingActionButton fabAddPet = findViewById(R.id.fabAddPet);
        fabAddPet.setOnClickListener(v ->
                startActivity(new Intent(this, AddPetActivity.class))
        );

        loadPets(recyclerView);

        // 🔙 MODERN BACK HANDLER (Gesture + Button)
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                // Fragment açıksa kapat
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {

                    getSupportFragmentManager().popBackStack();

                    if (fragmentContainer != null) {
                        fragmentContainer.setVisibility(View.GONE);
                    }

                } else {
                    // Fragment yoksa normal geri davranışı
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });

        // Toolbar geri tuşu → aynı back dispatcher
        toolbar.setNavigationOnClickListener(v ->
                getOnBackPressedDispatcher().onBackPressed()
        );
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

    // 🧩 FRAGMENT AÇMA (Pet → Aşı Listesi)
    public void openVaccineFragment(int petId) {
        if (fragmentContainer == null) return;

        fragmentContainer.setVisibility(View.VISIBLE);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.fragmentContainer,
                        VaccineListFragment.newInstance(petId)
                )
                .addToBackStack(null)
                .commit();
    }
}
