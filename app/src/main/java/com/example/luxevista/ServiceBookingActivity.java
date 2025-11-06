package com.example.luxevista;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ServiceBookingActivity extends AppCompatActivity {
    private EditText userNameEditText, userEmailEditText, userPhoneEditText, bookingDateEditText, userQueryEditText;
    private TextView selectedServiceTextView;
    private Button submitBookingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_booking);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userNameEditText = findViewById(R.id.user_name);
        userEmailEditText = findViewById(R.id.user_email);
        userPhoneEditText = findViewById(R.id.user_phone);
        bookingDateEditText = findViewById(R.id.booking_date);
        userQueryEditText = findViewById(R.id.user_query);
        selectedServiceTextView = findViewById(R.id.selected_service);
        submitBookingButton = findViewById(R.id.submit_booking_button);

        String selectedService = getIntent().getStringExtra("SERVICE_TITLE");
        selectedServiceTextView.setText(selectedService);

        submitBookingButton.setOnClickListener(v -> submitBooking());
    }

    private void submitBooking() {
        String name = userNameEditText.getText().toString().trim();
        String email = userEmailEditText.getText().toString().trim();
        String phone = userPhoneEditText.getText().toString().trim();
        String bookingDate = bookingDateEditText.getText().toString().trim();
        String query = userQueryEditText.getText().toString().trim();
        String service = selectedServiceTextView.getText().toString();

        Log.d("ServiceBookingActivity", "Name: " + name);
        Log.d("ServiceBookingActivity", "Email: " + email);
        Log.d("ServiceBookingActivity", "Phone: " + phone);
        Log.d("ServiceBookingActivity", "Booking Date: " + bookingDate);
        Log.d("ServiceBookingActivity", "Query: " + query);
        Log.d("ServiceBookingActivity", "Service: " + service);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        boolean isInserted = dbHelper.insertServiceBooking(name, bookingDate, phone, query, service);

        if (isInserted) {
            Toast.makeText(this, "Booking successful!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Booking failed.", Toast.LENGTH_SHORT).show();
        }
    }
}
