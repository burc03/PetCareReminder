package com.example.petcarereminder.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petcarereminder.R;
import com.example.petcarereminder.data.local.AppDatabase;
import com.example.petcarereminder.data.local.VaccineEntity;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Calendar;

public class AddVaccineActivity extends AppCompatActivity {

    // Author: Burcu Arıcı

    private int petId;
    private EditText etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccine);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        petId = getIntent().getIntExtra("pet_id", -1);

        EditText etTitle = findViewById(R.id.etVaccineTitle);
        etDate = findViewById(R.id.etVaccineDate);
        EditText etNote = findViewById(R.id.etVaccineNote);
        Button btnSave = findViewById(R.id.btnSaveVaccine);

        etDate.setOnClickListener(v -> showDatePicker());

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String date = etDate.getText().toString().trim();
            String note = etNote.getText().toString().trim();

            if (title.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Aşı adı ve tarih zorunlu", Toast.LENGTH_SHORT).show();
                return;
            }

            VaccineEntity vaccine = new VaccineEntity();
            vaccine.petId = petId;
            vaccine.title = title;
            vaccine.date = date;
            vaccine.note = note;

            AppDatabase.getInstance(this)
                    .vaccineDao()
                    .insert(vaccine);

            Toast.makeText(this, "Aşı eklendi", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void showDatePicker() {
        Calendar c = Calendar.getInstance();

        new DatePickerDialog(
                this,
                (view, year, month, day) ->
                        etDate.setText(day + "/" + (month + 1) + "/" + year),
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
        ).show();
    }
}
