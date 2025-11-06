package com.example.luxevista;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class RoomBookingAdapter extends RecyclerView.Adapter<RoomBookingAdapter.ViewHolder> {
    private ArrayList<RoomBooking> bookingList;
    public RoomBookingAdapter(ArrayList<RoomBooking> bookingList) {
        this.bookingList = bookingList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room_booking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RoomBooking booking = bookingList.get(position);
        holder.usernameTextView.setText(booking.getUsername());
        holder.bookingDateTextView.setText(booking.getBookingDate());
        holder.phoneTextView.setText(booking.getPhone());
        holder.queryTextView.setText(booking.getQuery());
        holder.roomTypeTextView.setText(booking.getRoomType());
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView, bookingDateTextView, phoneTextView, queryTextView, roomTypeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.text_usernameq);
            bookingDateTextView = itemView.findViewById(R.id.text_booking_dateq);
            phoneTextView = itemView.findViewById(R.id.text_phone_numberq);
            queryTextView = itemView.findViewById(R.id.text_queryq);
            roomTypeTextView = itemView.findViewById(R.id.text_room_titleq);
        }
    }
}
