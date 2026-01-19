package com.example.petcarereminder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcarereminder.R;
import com.example.petcarereminder.data.local.VaccineEntity;

import java.util.List;

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.VaccineViewHolder> {

    private final Context context;
    private final List<VaccineEntity> list;

    public VaccineAdapter(Context context, List<VaccineEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VaccineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_vaccine, parent, false);
        return new VaccineViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccineViewHolder holder, int position) {
        VaccineEntity v = list.get(position);
        holder.tvTitle.setText(v.title);
        holder.tvDate.setText(v.date);
        holder.tvNote.setText(v.note);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class VaccineViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate, tvNote;

        public VaccineViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvVaccineTitle);
            tvDate = itemView.findViewById(R.id.tvVaccineDate);
            tvNote = itemView.findViewById(R.id.tvVaccineNote);
        }
    }
}
