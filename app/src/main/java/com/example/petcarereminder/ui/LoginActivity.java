package com.example.petcarereminder.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.graphics.drawable.Drawable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.example.petcarereminder.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    // Author: Burcu Arıcı
    // Feature: Login & Splash Screen handling (API 25 compatible)

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ⏳ Splash ekranın görünmesi için kısa gecikme
        new Handler().postDelayed(() -> {

            setContentView(R.layout.activity_login);

            prefs = getSharedPreferences("login_prefs", MODE_PRIVATE);

            // 🔹 Daha önce hatırlandıysa direkt Main
            if (prefs.getBoolean("remember", false)) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return;
            }

            TextInputEditText etEmail = findViewById(R.id.etEmail);
            TextInputEditText etPassword = findViewById(R.id.etPassword);
            CheckBox cbRememberMe = findViewById(R.id.cbRememberMe);
            Button btnLogin = findViewById(R.id.btnLogin);
            TextView tvRegister = findViewById(R.id.tvRegister);

            // 🔹 Boş alana tıklayınca klavyeyi kapat
            findViewById(R.id.loginRoot).setOnClickListener(v -> hideKeyboard());

            btnLogin.setOnClickListener(v -> {
                String email = etEmail.getText() == null ? "" : etEmail.getText().toString().trim();
                String password = etPassword.getText() == null ? "" : etPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    showWarningPopup("Email ve şifre zorunludur");
                    return;
                }

                // 🔹 DEMO AMAÇLI SABİT GİRİŞ
                if (email.equals("test@test.com") && password.equals("1234")) {

                    if (cbRememberMe.isChecked()) {
                        prefs.edit().putBoolean("remember", true).apply();
                    }

                    startActivity(new Intent(this, MainActivity.class));
                    finish();

                } else {
                    showWarningPopup("Email veya şifre hatalı");
                }
            });

            // 🔹 Üye Ol → Register
            tvRegister.setOnClickListener(v ->
                    startActivity(new Intent(this, RegisterActivity.class))
            );

        }, 1500); // ⏱ 0.8 saniye splash süresi
    }

    // ❗ UYARI POPUP (KIRMIZI İKON)
    private void showWarningPopup(String message) {
        Drawable icon = ContextCompat.getDrawable(this, android.R.drawable.ic_dialog_alert);
        if (icon != null) {
            icon = DrawableCompat.wrap(icon);
            DrawableCompat.setTint(icon,
                    ContextCompat.getColor(this, android.R.color.holo_red_dark));
        }

        new MaterialAlertDialogBuilder(this)
                .setIcon(icon)
                .setTitle("Uyarı")
                .setMessage(message)
                .setPositiveButton("Tamam", null)
                .show();
    }

    // 🔹 Klavyeyi gizleme
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            view.clearFocus();
        }
    }
}
