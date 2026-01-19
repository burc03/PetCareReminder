package com.example.petcarereminder.ui.vaccine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcarereminder.R;
import com.example.petcarereminder.adapter.VaccineAdapter;
import com.example.petcarereminder.data.local.AppDatabase;
import com.example.petcarereminder.data.local.VaccineEntity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class VaccineListFragment extends Fragment {

    private static final String ARG_PET_ID = "pet_id";

    private int petId;
    private RecyclerView recyclerView;

    public static VaccineListFragment newInstance(int petId) {
        VaccineListFragment fragment = new VaccineListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PET_ID, petId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            petId = getArguments().getInt(ARG_PET_ID, -1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_vaccine_list, container, false);

        if (petId == -1) {
            Toast.makeText(requireContext(), "Hayvan bulunamadı", Toast.LENGTH_SHORT).show();
            requireActivity().onBackPressed();
            return view;
        }

        // Toolbar
        MaterialToolbar toolbar = view.findViewById(R.id.topAppBar);
        toolbar.setNavigationOnClickListener(v ->
                requireActivity().onBackPressed()
        );

        recyclerView = view.findViewById(R.id.recyclerVaccines);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        FloatingActionButton fab = view.findViewById(R.id.fabAddVaccine);
        fab.setOnClickListener(v ->
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(
                                R.id.fragmentContainer,
                                AddVaccineFragment.newInstance(petId)
                        )
                        .addToBackStack(null)
                        .commit()
        );

        loadVaccines();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadVaccines();
    }

    private void loadVaccines() {
        List<VaccineEntity> vaccines =
                AppDatabase.getInstance(requireContext())
                        .vaccineDao()
                        .getByPetId(petId);

        recyclerView.setAdapter(
                new VaccineAdapter(requireContext(), vaccines)
        );
    }
}
