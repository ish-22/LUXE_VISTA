package com.example.luxevista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Bookingbutton extends AppCompatActivity {
    private Button OfferButton;
    private Button ServiceButton;
    private Button  RoomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bookingbutton);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //view offer bookings
        OfferButton = findViewById(R.id.buttonBookingDetails1);
        OfferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bookingbutton.this, BookingDetailsActivity.class);
                startActivity(intent);
            }
        });

        //view service bookings
        ServiceButton = findViewById(R.id.buttonBookingDetails3);
        ServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bookingbutton.this, ServiceBookingDetailsActivity.class);
                startActivity(intent);
            }
        });

        //view room bookings
        RoomButton = findViewById(R.id.buttonBookingDetails2);
        RoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bookingbutton.this, RoomBookingsDisplayActivity.class);
                startActivity(intent);
            }
        });
    }
}