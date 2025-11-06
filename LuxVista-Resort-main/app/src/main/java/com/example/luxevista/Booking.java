package com.example.luxevista;

public class Booking {
    private String username;
    private String bookingDate;
    private String phoneNumber;
    private String query;
    private String offerTitle;

    public Booking(String username, String bookingDate, String phoneNumber, String query, String offerTitle) {
        this.username = username;
        this.bookingDate = bookingDate;
        this.phoneNumber = phoneNumber;
        this.query = query;
        this.offerTitle = offerTitle;
    }

    public String getUsername() { return username; }
    public String getBookingDate() { return bookingDate; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getQuery() { return query; }
    public String getOfferTitle() { return offerTitle; }
}
