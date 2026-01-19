package com.example.petcarereminder.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcarereminder.R;
import com.example.petcarereminder.data.local.AppDatabase;
import com.example.petcarereminder.data.local.PetEntity;
import com.example.petcarereminder.ui.AddPetActivity;
import com.example.petcarereminder.ui.VaccineListActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder> {

    private final Context context;
    private final List<PetEntity> petList;

    public PetAdapter(Context context, List<PetEntity> petList) {
        this.context = context;
        this.petList = petList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_pet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PetEntity pet = petList.get(position);

        holder.tvPetName.setText(pet.name);
        holder.tvPetInfo.setText(pet.type + " • " + pet.age + " yaş");

        // 🐾 ICON → AŞI LİSTESİ
        holder.imgPet.setOnClickListener(v -> {
            Intent intent = new Intent(context, VaccineListActivity.class);
            intent.putExtra("pet_id", pet.id);
            context.startActivity(intent);
        });

        // ✏️ DÜZENLE
        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddPetActivity.class);
            intent.putExtra("pet_id", pet.id);
            context.startActivity(intent);
        });

        // 🗑️ SİL
        holder.btnDelete.setOnClickListener(v ->
                new MaterialAlertDialogBuilder(context)
                        .setTitle("Hayvan Sil")
                        .setMessage(pet.name + " silinsin mi?")
                        .setPositiveButton("Sil", (d, w) -> {

                            int pos = holder.getAdapterPosition();
                            if (pos != RecyclerView.NO_POSITION) {

                                AppDatabase.getInstance(context)
                                        .petDao()
                                        .delete(pet);

                                petList.remove(pos);
                                notifyItemRemoved(pos);
                            }
                        })
                        .setNegativeButton("İptal", null)
                        .show()
        );
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    // =======================
    // VIEW HOLDER
    // =======================
    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPet;
        TextView tvPetName, tvPetInfo;
        ImageButton btnEdit, btnDelete;

        ViewHolder(View v) {
            super(v);
            imgPet = v.findViewById(R.id.imgPet);
            tvPetName = v.findViewById(R.id.tvPetName);
            tvPetInfo = v.findViewById(R.id.tvPetInfo);
            btnEdit = v.findViewById(R.id.btnEdit);
            btnDelete = v.findViewById(R.id.btnDelete);
        }
    }
}
