package com.example.luxevista;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RoomBookingsDisplayActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RoomBookingAdapter adapter;
    private ArrayList<RoomBooking> bookingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_bookings_display);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        bookingList = dbHelper.getAllRoomBookingsList();

        adapter = new RoomBookingAdapter(bookingList);
        recyclerView.setAdapter(adapter);
    }
}
