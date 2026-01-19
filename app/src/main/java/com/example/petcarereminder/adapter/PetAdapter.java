package com.example.petcarereminder.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcarereminder.R;
import com.example.petcarereminder.data.PetRepository;
import com.example.petcarereminder.model.PetModel;
import com.example.petcarereminder.ui.AddPetActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    private final List<PetModel> petList;
    private final Context context;

    public PetAdapter(Context context, List<PetModel> petList) {
        this.context = context;
        this.petList = petList;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_pet, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        PetModel pet = petList.get(position);

        holder.tvName.setText(pet.getName());
        holder.tvInfo.setText(pet.getType() + " • " + pet.getAge() + " yaş");

        // ✏️ DÜZENLE
        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddPetActivity.class);
            intent.putExtra("edit_mode", true);
            intent.putExtra("pet_index", holder.getBindingAdapterPosition());
            context.startActivity(intent);
        });

        // 🗑️ SİL (ONAYLI)
        holder.btnDelete.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos == RecyclerView.NO_POSITION) return;

            new MaterialAlertDialogBuilder(context)
                    .setTitle("Hayvan Sil")
                    .setMessage(pet.getName() + " silinsin mi?")
                    .setPositiveButton("Sil", (dialog, which) -> {
                        PetRepository.removePet(pos);
                        notifyItemRemoved(pos);
                    })
                    .setNegativeButton("İptal", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    static class PetViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvInfo;
        ImageButton btnEdit, btnDelete;

        public PetViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvPetName);
            tvInfo = itemView.findViewById(R.id.tvPetInfo);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
