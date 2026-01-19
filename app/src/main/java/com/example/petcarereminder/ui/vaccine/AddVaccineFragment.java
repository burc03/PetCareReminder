package com.example.petcarereminder.ui.vaccine;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.petcarereminder.R;
import com.example.petcarereminder.data.local.AppDatabase;
import com.example.petcarereminder.data.local.VaccineEntity;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Calendar;

public class AddVaccineFragment extends Fragment {

    private static final String ARG_PET_ID = "pet_id";

    private int petId;
    private EditText etDate;

    public static AddVaccineFragment newInstance(int petId) {
        AddVaccineFragment fragment = new AddVaccineFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PET_ID, petId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            petId = getArguments().getInt(ARG_PET_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_add_vaccine, container, false);

        MaterialToolbar toolbar = view.findViewById(R.id.topAppBar);
        toolbar.setNavigationOnClickListener(v ->
                requireActivity().onBackPressed()
        );

        EditText etTitle = view.findViewById(R.id.etVaccineTitle);
        etDate = view.findViewById(R.id.etVaccineDate);
        EditText etNote = view.findViewById(R.id.etVaccineNote);
        Button btnSave = view.findViewById(R.id.btnSaveVaccine);

        etDate.setOnClickListener(v -> showDatePicker());

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String date = etDate.getText().toString().trim();
            String note = etNote.getText().toString().trim();

            if (title.isEmpty() || date.isEmpty()) {
                Toast.makeText(requireContext(),
                        "Aşı adı ve tarih zorunlu",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            VaccineEntity vaccine = new VaccineEntity();
            vaccine.petId = petId;
            vaccine.title = title;
            vaccine.date = date;
            vaccine.note = note;

            AppDatabase.getInstance(requireContext())
                    .vaccineDao()
                    .insert(vaccine);

            Toast.makeText(requireContext(),
                    "Aşı eklendi",
                    Toast.LENGTH_SHORT).show();

            requireActivity().onBackPressed();
        });

        return view;
    }

    private void showDatePicker() {
        Calendar c = Calendar.getInstance();

        new DatePickerDialog(
                requireContext(),
                (view, year, month, day) ->
                        etDate.setText(day + "/" + (month + 1) + "/" + year),
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
        ).show();
    }
}
