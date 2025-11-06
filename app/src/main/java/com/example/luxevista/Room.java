package com.example.luxevista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Room extends AppCompatActivity {
    private Button[] bookNowButtons = new Button[10]; // Array for 10 buttons
    private String roomTitle; // Declare the room title variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room);

        // Initialize buttons and room title
        for (int i = 0; i < bookNowButtons.length; i++) {
            int resID = getResources().getIdentifier("book_now_button_" + (i + 1), "id", getPackageName());
            bookNowButtons[i] = findViewById(resID);
            int finalI = i; // To avoid closure issues
            bookNowButtons[i].setOnClickListener(view -> {
                roomTitle = "Room " + (finalI + 1); // Set room title based on button
                Intent intent = new Intent(Room.this, BookingFormActivityRoom.class);
                intent.putExtra("ROOM_TITLE", roomTitle); // Pass the title of the room
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
