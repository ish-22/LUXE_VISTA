package com.example.luxevista;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookingFormActivityRoom extends AppCompatActivity {
    private TextView tvRoomTitle;
    private EditText etName, etEmail, etBookingDate, etSpecialRequests;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form_room);

        // Initialize views
        tvRoomTitle = findViewById(R.id.tvRoomTitle);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etBookingDate = findViewById(R.id.etBookingDate);
        etSpecialRequests = findViewById(R.id.etSpecialRequests);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Get the room title passed from the previous activity
        String roomTitle = getIntent().getStringExtra("ROOM_TITLE");
        tvRoomTitle.setText(roomTitle);

        // Set up the submit button click listener
        btnSubmit.setOnClickListener(view -> {
            submitBooking();
        });
    }

    private void submitBooking() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String bookingDate = etBookingDate.getText().toString().trim();
        String specialRequests = etSpecialRequests.getText().toString().trim();

        // Validate inputs (you can add more validation as needed)
        if (name.isEmpty() || email.isEmpty() || bookingDate.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Here, you would typically save the booking details to the database
        // For example, using SQLite or an API call

        // Display a success message (you can modify this as needed)
        Toast.makeText(this, "Booking submitted successfully!", Toast.LENGTH_SHORT).show();

        // Optionally, finish the activity or redirect to another screen
        finish();
    }
}
