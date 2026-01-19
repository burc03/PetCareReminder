package com.example.petcarereminder.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petcarereminder.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 🔹 Toolbar
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        // Başlığı kapat (logo kalır)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // 🔹 View’ler
        MaterialCardView cardAddPet = findViewById(R.id.cardAddPet);
        MaterialCardView cardPetList = findViewById(R.id.cardPetList);
        Button btnLogout = findViewById(R.id.btnLogout);

        // 🐶 HAYVAN EKLE → AddPetActivity AÇ
        cardAddPet.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddPetActivity.class);
            startActivity(intent);
        });

        // 📋 HAYVAN LİSTESİ (şimdilik demo)
        cardPetList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PetListActivity.class);
            startActivity(intent);
        });

        // 🔴 ÇIKIŞ BUTONU
        btnLogout.setOnClickListener(v -> showLogoutDialog());

        // 👤 PROFİL MENÜ (sağ üst)
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_profile) {
                showProfileDialog();
                return true;
            }
            return false;
        });
    }

    // 👤 Profil Bilgileri
    private void showProfileDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Kullanıcı Bilgileri")
                .setMessage("Email: test@test.com")
                .setPositiveButton("Çıkış Yap", (dialog, which) -> logout())
                .setNegativeButton("Kapat", null)
                .show();
    }

    // 🔴 Çıkış Onayı
    private void showLogoutDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Çıkış")
                .setMessage("Çıkış yapmak istiyor musunuz?")
                .setPositiveButton("Evet", (dialog, which) -> logout())
                .setNegativeButton("Hayır", null)
                .show();
    }

    // 🔐 Logout işlemi
    private void logout() {
        SharedPreferences prefs =
                getSharedPreferences("login_prefs", MODE_PRIVATE);
        prefs.edit().clear().apply();

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
