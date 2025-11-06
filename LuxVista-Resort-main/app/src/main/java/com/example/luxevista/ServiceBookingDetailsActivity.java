package com.example.luxevista;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;


public class ServiceBookingDetailsActivity extends AppCompatActivity {
    private TextView bookingDetailsTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_service_booking_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bookingDetailsTextView = findViewById(R.id.booking_details);

        String userName = getIntent().getStringExtra("USER_NAME");
        String userEmail = getIntent().getStringExtra("USER_EMAIL");
        String userPhone = getIntent().getStringExtra("USER_PHONE");
        String bookingDate = getIntent().getStringExtra("BOOKING_DATE");
        String userQuery = getIntent().getStringExtra("USER_QUERY");
        String serviceTitle = getIntent().getStringExtra("SERVICE_TITLE");

        String bookingDetails = "Name: " + userName + "\n" +
                "Email: " + userEmail + "\n" +
                "Phone: " + userPhone + "\n" +
                "Booking Date: " + bookingDate + "\n" +
                "Query: " + userQuery + "\n" +
                "Service: " + serviceTitle;

        bookingDetailsTextView.setText(bookingDetails);
    }
}