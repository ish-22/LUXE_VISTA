package com.example.luxevista;
public class RoomBooking {
    private String username;
    private String bookingDate;
    private String phone;
    private String query;
    private String roomType;
    public RoomBooking(String username, String bookingDate, String phone, String query, String roomType) {
        this.username = username;
        this.bookingDate = bookingDate;
        this.phone = phone;
        this.query = query;
        this.roomType = roomType;
    }
    public String getUsername() { return username; }
    public String getBookingDate() { return bookingDate; }
    public String getPhone() { return phone; }
    public String getQuery() { return query; }
    public String getRoomType() { return roomType; }
}
