package com.example.luxevista;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookingDetailsActivity extends AppCompatActivity {

    private RecyclerView bookingsRecyclerView;
    private BookingAdapter bookingAdapter;
    private ArrayList<Booking> bookingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        // Set padding for insets (if required)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recycler_view_bookings), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize RecyclerView
        bookingsRecyclerView = findViewById(R.id.recycler_view_bookings);
        bookingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch bookings from the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        bookingList = new ArrayList<>();
        loadBookings(dbHelper);

        // Set the adapter
        bookingAdapter = new BookingAdapter(bookingList);
        bookingsRecyclerView.setAdapter(bookingAdapter);
    }

    private void loadBookings(DatabaseHelper dbHelper) {
        Cursor cursor = dbHelper.getAllBookings();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME));
                @SuppressLint("Range") String bookingDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_BOOKING_DATE));
                @SuppressLint("Range") String phoneNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE_NUMBER));
                @SuppressLint("Range") String query = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_QUERY));
                @SuppressLint("Range") String offerTitle = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OFFER_TITLE));

                bookingList.add(new Booking(username, bookingDate, phoneNumber, query, offerTitle));
            } while (cursor.moveToNext());
            cursor.close();
        }
    }
}
