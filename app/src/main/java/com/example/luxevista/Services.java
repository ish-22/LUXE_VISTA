package com.example.luxevista;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Services extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_services);

        // Apply window insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Call the setup method to initialize buttons
        setupServiceButtons();
    }

    private void setupServiceButtons() {
        // Set up click listeners for each "Book Now" button
        findViewById(R.id.book_spa_button).setOnClickListener(v -> bookService("Spa Appointment"));
        findViewById(R.id.book_dining_button).setOnClickListener(v -> bookService("Fine Dining Reservations"));
        findViewById(R.id.book_poolside_button).setOnClickListener(v -> bookService("Poolside Cabanas"));
        findViewById(R.id.book_fitness_button).setOnClickListener(v -> bookService("Fitness Class"));
        findViewById(R.id.book_room_service_button).setOnClickListener(v -> bookService("Room Service"));
    }

    private void bookService(String serviceTitle) {
        Intent intent = new Intent(this, ServiceBookingActivity.class);
        intent.putExtra("SERVICE_TITLE", serviceTitle);
        startActivity(intent);
    }
}
