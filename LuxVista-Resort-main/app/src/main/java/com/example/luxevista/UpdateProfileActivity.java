package com.example.luxevista;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateProfileActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private EditText editTextUsername, editTextEmail, editTextPassword, editTextConfirmPassword;
    private String currentUsername, currentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        // Configure window insets (ensure ID "main" exists in XML)
        View mainLayout = findViewById(R.id.main);
        if (mainLayout != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainLayout, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        db = new DatabaseHelper(this);

        // Retrieve current user details from Intent
        currentUsername = getIntent().getStringExtra("currentUsername");
        currentEmail = getIntent().getStringExtra("currentEmail");

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        Button btnUpdate = findViewById(R.id.btnUpdate);

        // Populate current details in EditText fields
        editTextUsername.setText(currentUsername);
        editTextEmail.setText(currentEmail);

        btnUpdate.setOnClickListener(v -> {
            String newUsername = editTextUsername.getText().toString().trim();
            String newEmail = editTextEmail.getText().toString().trim();
            String newPassword = editTextPassword.getText().toString().trim();
            String confirmPassword = editTextConfirmPassword.getText().toString().trim();

            // Validate input fields
            if (newUsername.isEmpty() || newEmail.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(UpdateProfileActivity.this, "All fields are required.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(UpdateProfileActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Update user details in the database
            boolean isUpdated = db.updateUserDetails(currentUsername, newUsername, newEmail);
            if (isUpdated) {
                db.updatePassword(newUsername, newPassword);
                Toast.makeText(UpdateProfileActivity.this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                currentUsername = newUsername; // Update local variables
                currentEmail = newEmail;
            } else {
                Toast.makeText(UpdateProfileActivity.this, "Update failed. Try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
