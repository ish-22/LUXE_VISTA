package com.example.luxevista;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Homepage extends AppCompatActivity {
    private Button move;
    private Button logout;
    private Button faqButton;
    private Button feedbackButton;
    private ImageView offerImageView;
    private ImageView Booking;
    private ImageView Profile;
    private ImageView Rooms;
    private ImageView Services;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homepage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //view about us
        move = findViewById(R.id.movetoaboutus);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

        // Initialize the FAQ button
        faqButton = findViewById(R.id.faqbutton);
        // Set an OnClickListener to navigate to the FAQ page
        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, FAQActivity.class);
                startActivity(intent);
            }
        });

        // Initialize the Feedbacks button
        feedbackButton = findViewById(R.id.feedbackbutton);

        // Set an OnClickListener to navigate to the FAQ page
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Feedback.class);
                startActivity(intent);
            }
        });

        // Example of the logged-in user's email
        String userEmail = "user@example.com"; // Replace with actual logic to get the user's email

        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feedbackIntent = new Intent(Homepage.this, Feedback.class);
                feedbackIntent.putExtra("USER_EMAIL", userEmail); // Pass the user's email
                startActivity(feedbackIntent);
            }
        });

        //logout function
        logout = findViewById(R.id.movetomainactivity);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an AlertDialog to confirm logout
                new AlertDialog.Builder(Homepage.this)
                        .setTitle("Logout")
                        .setMessage("Do you want to logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // If the user confirms, navigate to MainActivity
                                Intent intent = new Intent(Homepage.this, MainActivity.class);
                                startActivity(intent);
                                finish(); // Optionally, close the Homepage activity
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // If the user cancels, close the dialog and stay on the page
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        ImageView offerImageView = findViewById(R.id.offers);
        offerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Offers.class);
                startActivity(intent);
            }
        });

        ImageView Booking = findViewById(R.id.booking);
        Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Bookingbutton.class);
                startActivity(intent);
            }
        });

        ImageView Profile = findViewById(R.id.profile);
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, UpdateProfileActivity.class);
                startActivity(intent);
            }
        });

        ImageView Rooms = findViewById(R.id.rooms);
        Rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Room.class);
                startActivity(intent);
            }
        });

        ImageView Services = findViewById(R.id.services);
        Services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Services.class);
                startActivity(intent);
            }
        });

        }
    }
