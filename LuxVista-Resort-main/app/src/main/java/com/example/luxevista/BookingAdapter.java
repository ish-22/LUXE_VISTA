package com.example.luxevista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private ArrayList<Booking> bookingList;

    public BookingAdapter(ArrayList<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        holder.textUsername.setText("Username: " + booking.getUsername());
        holder.textBookingDate.setText("Booking Date: " + booking.getBookingDate());
        holder.textPhoneNumber.setText("Phone: " + booking.getPhoneNumber());
        holder.textQuery.setText("Query: " + booking.getQuery());
        holder.textOfferTitle.setText("Offer: " + booking.getOfferTitle());
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView textUsername, textBookingDate, textPhoneNumber, textQuery, textOfferTitle;

        public BookingViewHolder(View itemView) {
            super(itemView);
            textUsername = itemView.findViewById(R.id.text_username);
            textBookingDate = itemView.findViewById(R.id.text_booking_date);
            textPhoneNumber = itemView.findViewById(R.id.text_phone_number);
            textQuery = itemView.findViewById(R.id.text_query);
            textOfferTitle = itemView.findViewById(R.id.text_offer_title);
        }
    }
}
