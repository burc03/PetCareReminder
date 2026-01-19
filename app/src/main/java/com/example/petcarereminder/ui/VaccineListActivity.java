package com.example.petcarereminder.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcarereminder.R;
import com.example.petcarereminder.adapter.VaccineAdapter;
import com.example.petcarereminder.data.local.AppDatabase;
import com.example.petcarereminder.data.local.VaccineEntity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class VaccineListActivity extends AppCompatActivity {

    private int petId;
    private VaccineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_list);

        // 🔙 Toolbar
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        // 🔑 Seçilen hayvan ID
        petId = getIntent().getIntExtra("pet_id", -1);
        if (petId == -1) {
            Toast.makeText(this, "Hayvan bulunamadı", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerVaccines);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fabAdd = findViewById(R.id.fabAddVaccine);
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddVaccineActivity.class);
            intent.putExtra("pet_id", petId);
            startActivity(intent);
        });

        loadVaccines(recyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.recyclerVaccines);
        loadVaccines(recyclerView);
    }

    private void loadVaccines(RecyclerView recyclerView) {

        // ✅ DOĞRU METOD ADI
        List<VaccineEntity> vaccines =
                AppDatabase.getInstance(this)
                        .vaccineDao()
                        .getByPetId(petId);

        adapter = new VaccineAdapter(this, vaccines);
        recyclerView.setAdapter(adapter);
    }
}
