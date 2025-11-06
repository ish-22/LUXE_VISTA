package com.example.luxevista;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Feedback extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private TextView userNameTextView;
    private EditText feedbackEditText;
    private RatingBar ratingBar;
    private Button submitButton;
    private LinearLayout feedbackDisplay; // Changed TextView to LinearLayout to hold multiple feedback items

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        databaseHelper = new DatabaseHelper(this);
        userNameTextView = findViewById(R.id.user_name);
        feedbackEditText = findViewById(R.id.feedback_edit_text);
        ratingBar = findViewById(R.id.rating_bar);
        submitButton = findViewById(R.id.submit_button);
        feedbackDisplay = findViewById(R.id.feedback_display); // Ensure this is a LinearLayout in XML

        // Get the logged-in user's email from the Intent
        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("USER_EMAIL");

        if (userEmail == null) {
            Toast.makeText(this, "User email not provided", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if the email is not provided
            return;
        }

        // Debugging log to check email
        Log.d("FeedbackActivity", "User email: " + userEmail);

        // Get the user's name from the database
        String userName = databaseHelper.getUsernameByEmail(userEmail);
        if (userName != null) {
            userNameTextView.setText("Welcome, " + userName); // Display username
        } else {
            userNameTextView.setText("Welcome, Guest"); // Fallback if username not found
        }

        loadFeedback();  // Load any existing feedback to display

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitFeedback(userName); // Pass the user's name to submit feedback
            }
        });
    }

    private void submitFeedback(String username) {
        String feedbackText = feedbackEditText.getText().toString().trim();
        float rating = ratingBar.getRating();

        if (feedbackText.isEmpty()) {
            Toast.makeText(this, "Please enter your feedback", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = databaseHelper.insertFeedback(username, feedbackText, rating);
        if (result != -1) {
            Toast.makeText(this, "Feedback submitted successfully", Toast.LENGTH_SHORT).show();
            feedbackEditText.setText(""); // Clear input field
            ratingBar.setRating(0); // Reset rating
            loadFeedback(); // Refresh feedback display
        } else {
            Toast.makeText(this, "Failed to submit feedback", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadFeedback() {
        Cursor cursor = databaseHelper.getAllFeedback();
        feedbackDisplay.removeAllViews(); // Clear previous views

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String feedbackText = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FEEDBACK));
                @SuppressLint("Range") float rating = cursor.getFloat(cursor.getColumnIndex(DatabaseHelper.COLUMN_RATING));

                // Create a container for each feedback entry
                LinearLayout feedbackContainer = new LinearLayout(this);
                feedbackContainer.setOrientation(LinearLayout.VERTICAL);
                feedbackContainer.setBackgroundResource(android.R.color.white);
                feedbackContainer.setPadding(16, 16, 16, 16);
                feedbackContainer.setElevation(4f);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 0, 16); // Space between entries
                feedbackContainer.setLayoutParams(params);

                // Rating Text
                TextView ratingText = new TextView(this);
                ratingText.setText("Rating: " + rating);
                ratingText.setTextColor(Color.parseColor("#4CAF50"));
                ratingText.setTextSize(16);
                feedbackContainer.addView(ratingText);

                // Feedback Text
                TextView feedbackTextView = new TextView(this);
                feedbackTextView.setText(feedbackText);
                feedbackTextView.setTextColor(Color.BLACK);
                feedbackTextView.setTextSize(16);
                feedbackContainer.addView(feedbackTextView);

                // Add each feedback entry to the display section
                feedbackDisplay.addView(feedbackContainer);

            } while (cursor.moveToNext());
            cursor.close();
        } else {
            TextView noFeedbackText = new TextView(this);
            noFeedbackText.setText("No feedback submitted yet.");
            noFeedbackText.setTextColor(Color.DKGRAY);
            noFeedbackText.setTextSize(16);
            feedbackDisplay.addView(noFeedbackText);
        }
    }
}
