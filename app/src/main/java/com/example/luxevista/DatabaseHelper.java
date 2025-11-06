package com.example.luxevista;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database name and version
    private static final String DATABASE_NAME = "LuxeVista.db";
    private static final int DATABASE_VERSION = 5;

    // Common columns
    private static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_BOOKING_DATE = "booking_date";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_QUERY = "query";

    // Users table
    private static final String TABLE_USERS = "users";
    public static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    // Feedback table
    private static final String TABLE_FEEDBACK = "feedback";
    public static final String COLUMN_FEEDBACK = "feedback_text";
    public static final String COLUMN_RATING = "rating";

    // Bookings table
    private static final String TABLE_BOOKINGS = "bookings";
    public static final String COLUMN_OFFER_TITLE = "offer_title";

    // Service Bookings table
    private static final String TABLE_SERVICE_BOOKINGS = "service_bookings";
    private static final String COLUMN_SERVICE_TITLE = "serviceTitle";

    // Room bookings table
    public static final String TABLE_ROOM_BOOKINGS = "room_bookings";
    public static final String COLUMN_ROOM_NAME = "room_name";
    public static final String COLUMN_ROOM_BOOKING_DATE = "booking_date";
    public static final String COLUMN_ROOM_PHONE = "phone";
    public static final String COLUMN_ROOM_QUERY = "query";
    public static final String COLUMN_ROOM_TYPE = "room_type";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Users table
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_EMAIL + " TEXT UNIQUE, "
                + COLUMN_PASSWORD + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);

        // Create Feedback table
        String CREATE_FEEDBACK_TABLE = "CREATE TABLE " + TABLE_FEEDBACK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_FEEDBACK + " TEXT, "
                + COLUMN_RATING + " REAL)";
        db.execSQL(CREATE_FEEDBACK_TABLE);

        // Create Bookings table
        String CREATE_BOOKINGS_TABLE = "CREATE TABLE " + TABLE_BOOKINGS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_BOOKING_DATE + " TEXT, "
                + COLUMN_PHONE_NUMBER + " TEXT, "
                + COLUMN_QUERY + " TEXT, "
                + COLUMN_OFFER_TITLE + " TEXT)";
        db.execSQL(CREATE_BOOKINGS_TABLE);

        // Create Service Bookings table
        String CREATE_SERVICE_BOOKINGS_TABLE = "CREATE TABLE " + TABLE_SERVICE_BOOKINGS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_BOOKING_DATE + " TEXT, "
                + COLUMN_PHONE_NUMBER + " TEXT, "
                + COLUMN_QUERY + " TEXT, "
                + COLUMN_SERVICE_TITLE + " TEXT)";
        db.execSQL(CREATE_SERVICE_BOOKINGS_TABLE);


        // Create Room Bookings table
        String CREATE_ROOM_BOOKINGS_TABLE = "CREATE TABLE " + TABLE_ROOM_BOOKINGS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_ROOM_BOOKING_DATE + " TEXT, "
                + COLUMN_ROOM_PHONE + " TEXT, "
                + COLUMN_ROOM_QUERY + " TEXT, "
                + COLUMN_ROOM_TYPE + " TEXT)";
        db.execSQL(CREATE_ROOM_BOOKINGS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEEDBACK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKINGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_BOOKINGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM_BOOKINGS);
        onCreate(db);
    }


    // Insert room booking details
    public boolean insertRoomBooking(String username, String bookingDate, String phone, String query, String roomType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_ROOM_BOOKING_DATE, bookingDate);
        values.put(COLUMN_ROOM_PHONE, phone);
        values.put(COLUMN_ROOM_QUERY, query);
        values.put(COLUMN_ROOM_TYPE, roomType);

        long result = db.insert(TABLE_ROOM_BOOKINGS, null, values);
        db.close();
        return result != -1;
    }



    // Method to get all room bookings
    public Cursor getAllRoomBookings() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_ROOM_BOOKINGS, null, null, null, null, null, null);
    }

    // Method to insert service booking details
    public boolean insertServiceBooking(String username, String bookingDate, String phoneNumber, String query, String serviceTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_BOOKING_DATE, bookingDate);
        values.put(COLUMN_PHONE_NUMBER, phoneNumber);
        values.put(COLUMN_QUERY, query);
        values.put(COLUMN_SERVICE_TITLE, serviceTitle);

        long result = db.insert(TABLE_SERVICE_BOOKINGS, null, values);
        db.close();
        return result != -1;
    }


    // Method to add a new user
    public long addUser(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result;
    }

    // Method to validate user login
    public boolean validateUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?", new String[]{email, password});
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isValid;
    }

    // Method to check if an email already exists
    public boolean checkEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + "=?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Method to insert feedback
    public long insertFeedback(String username, String feedbackText, float rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_FEEDBACK, feedbackText);
        values.put(COLUMN_RATING, rating);

        long result = db.insert(TABLE_FEEDBACK, null, values);
        db.close();
        return result;
    }

    // Method to get all feedback
    public Cursor getAllFeedback() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_FEEDBACK, null);
    }


    // Method to insert booking details
    public long insertBooking(String username, String bookingDate, String phoneNumber, String query, String offerTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_BOOKING_DATE, bookingDate);
        values.put(COLUMN_PHONE_NUMBER, phoneNumber);
        values.put(COLUMN_QUERY, query);
        values.put(COLUMN_OFFER_TITLE, offerTitle);

        long result = db.insert(TABLE_BOOKINGS, null, values);
        db.close();
        return result;
    }

    // Method to get all bookings
    public Cursor getAllBookings() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_BOOKINGS, null);
    }




    // Method to get username by email
    public String getUsernameByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_USERNAME + " FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + "=?", new String[]{email});
        String username = null;
        if (cursor != null && cursor.moveToFirst()) {
            username = cursor.getString(0);
            cursor.close();
        }
        db.close();
        return username;
    }

    // Method to update user details
    public boolean updateUserDetails(String oldUsername, String newUsername, String newEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, newUsername);
        values.put(COLUMN_EMAIL, newEmail);

        int rowsAffected = db.update(TABLE_USERS, values, COLUMN_USERNAME + "=?", new String[]{oldUsername});
        db.close();
        return rowsAffected > 0;
    }

    // Method to update password
    public void updatePassword(String username, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PASSWORD, newPassword);
        db.update(TABLE_USERS, values, COLUMN_USERNAME + "=?", new String[]{username});
        db.close();
    }

    // Retrieve all room bookings
    public ArrayList<RoomBooking> getAllRoomBookingsList() {
        ArrayList<RoomBooking> bookingList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ROOM_BOOKINGS, null);

        if (cursor.moveToFirst()) {
            do {
               @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
               @SuppressLint("Range") String bookingDate = cursor.getString(cursor.getColumnIndex(COLUMN_ROOM_BOOKING_DATE));
               @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(COLUMN_ROOM_PHONE));
               @SuppressLint("Range") String query = cursor.getString(cursor.getColumnIndex(COLUMN_ROOM_QUERY));
               @SuppressLint("Range") String roomType = cursor.getString(cursor.getColumnIndex(COLUMN_ROOM_TYPE));

                RoomBooking booking = new RoomBooking(username, bookingDate, phone, query, roomType);
                bookingList.add(booking);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bookingList;
    }
}
