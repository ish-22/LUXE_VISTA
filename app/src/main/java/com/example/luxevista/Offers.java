package com.example.luxevista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Offers extends AppCompatActivity {

    private Button[] bookNowButtons = new Button[10]; // Array for 10 buttons
    private String offerTitle; // Declare the offer title variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_offers);

        // Initialize buttons and offer title
        for (int i = 0; i < bookNowButtons.length; i++) {
            int resID = getResources().getIdentifier("book_now_button_" + (i + 1), "id", getPackageName());
            bookNowButtons[i] = findViewById(resID);
            int finalI = i; // To avoid closure issues
            bookNowButtons[i].setOnClickListener(view -> {
                offerTitle = "Offer " + (finalI + 1); // Set offer title based on button
                Intent intent = new Intent(Offers.this, BookingFormActivity.class);
                intent.putExtra("OFFER_TITLE", offerTitle); // Pass the title of the offer
                startActivity(intent);
            });
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
