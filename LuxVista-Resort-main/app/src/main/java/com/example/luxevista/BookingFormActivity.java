package com.example.luxevista;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BookingFormActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText bookingDateEditText;
    private EditText queryEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize EditTexts
        nameEditText = findViewById(R.id.edit_text_name);
        emailEditText = findViewById(R.id.edit_text_email);
        phoneEditText = findViewById(R.id.edit_text_phone);
        bookingDateEditText = findViewById(R.id.edit_text_booking_date);
        queryEditText = findViewById(R.id.edit_text_query);

        // Initialize Button
        submitButton = findViewById(R.id.button_submit);

        // Get the selected offer title from Intent
        String selectedOfferTitle = getIntent().getStringExtra("OFFER_TITLE");
        if (selectedOfferTitle == null) {
            Toast.makeText(this, "No offer selected!", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if no offer is selected
        }

        submitButton.setOnClickListener(view -> {
            // Initialize the variables within the onClick listener
            String name = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String phoneNumber = phoneEditText.getText().toString().trim();
            String bookingDate = bookingDateEditText.getText().toString().trim();
            String query = queryEditText.getText().toString().trim();

            // Validate inputs
            if (name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || bookingDate.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return; // Exit if validation fails
            }

            // Insert booking into the database
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            long result = dbHelper.insertBooking(name, bookingDate, phoneNumber, query, selectedOfferTitle);

            if (result != -1) {
                Toast.makeText(this, "Booking Successful!", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity after submission
            } else {
                Toast.makeText(this, "Booking Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
