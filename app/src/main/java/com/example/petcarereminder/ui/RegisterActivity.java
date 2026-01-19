package com.example.petcarereminder.ui;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petcarereminder.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextInputEditText etEmail = findViewById(R.id.etEmail);
        TextInputEditText etPassword = findViewById(R.id.etPassword);
        TextInputEditText etPasswordAgain = findViewById(R.id.etPasswordAgain);
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView tvGoLogin = findViewById(R.id.tvGoLogin);


        // 🔹 Boş alana tıklayınca klavyeyi kapat
        findViewById(R.id.registerRoot).setOnClickListener(v -> hideKeyboard());

        // 🔹 Giriş Yap → Login ekranına dön
        tvGoLogin.setOnClickListener(v -> finish());

        btnRegister.setOnClickListener(v -> {
            String email = etEmail.getText() == null ? "" : etEmail.getText().toString().trim();
            String password = etPassword.getText() == null ? "" : etPassword.getText().toString().trim();
            String passwordAgain = etPasswordAgain.getText() == null ? "" : etPasswordAgain.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty() || passwordAgain.isEmpty()) {
                showWarningPopup("Tüm alanlar zorunludur");
                return;
            }

            if (!password.equals(passwordAgain)) {
                showWarningPopup("Şifreler uyuşmuyor");
                return;
            }

            showSuccessPopup();
            finish();
        });
    }

    // ❗ UYARI POPUP
    private void showWarningPopup(String message) {
        new MaterialAlertDialogBuilder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Uyarı")
                .setMessage(message)
                .setPositiveButton("Tamam", null)
                .show();
    }

    // ✅ BAŞARI POPUP
    private void showSuccessPopup() {
        new MaterialAlertDialogBuilder(this)
                .setIcon(android.R.drawable.checkbox_on_background)
                .setTitle("Başarılı")
                .setMessage("Üyelik başarıyla oluşturuldu")
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
