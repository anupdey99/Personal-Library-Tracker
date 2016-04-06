package com.adlabsbd.anup.personal_library_tracker.database;

/**
 * Created by Sudarshan Biswas on 19-Mar-16.
 */
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mobile App Develop on 19-3-16.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "library";

    static final String TABLE_BOOK = "book";
    static final String TABLE_BOOK_ID = "book_id";
    static final String TABLE_BOOK_ISBN = "book_isbn";
    static final String TABLE_BOOK_NAME = "book_name";
    static final String TABLE_BOOK_TYPE = "book_type";
    static final String TABLE_BOOK_AUTHOR = "book_author";
    static final String TABLE_BOOK_QUANTITY = "book_quantity";
    static final String TABLE_BOOK_PUBLISHER = "book_publisher";
    static final String TABLE_BOOK_EDITION = "book_edition";
    static final String TABLE_BOOK_LOCATION = "book_location";

    static final String TABLE_BORROW_NAME = "book_borrow";
    static final String TABLE_BORROW_ID = "borrow_id";
    static final String TABLE_USER_ID = "user_id";
    static final String TABLE_USER_NAME = "user_name";
    static final String TABLE_BORROW_ISBN_CODE = "isbn_code";

    String TABLE_BOOK_CREATE = "CREATE TABLE "+TABLE_BOOK+" ( "+ TABLE_BOOK_ID +" INTEGER PRIMARY KEY, "+ TABLE_BOOK_ISBN +" TEXT,"+ TABLE_BOOK_NAME +" TEXT, "+TABLE_BOOK_TYPE+" TEXT, "+TABLE_BOOK_AUTHOR+" TEXT, "+TABLE_BOOK_QUANTITY +" INTEGER, "+TABLE_BOOK_PUBLISHER+" TEXT,"+ TABLE_BOOK_EDITION+" TEXT, "+TABLE_BOOK_LOCATION+" TEXT ) ";
    //String TABLE_USER_CREATE = " CREATE TABLE "+ TABLE_USER+" ( user_tbl_id INTEGER PRIMARY KEY, "+TABLE_USER_NAME+" TEXT, "+TABLE_USER_ID+" TEXT) ";
    String TABLE_BOOK_BORROW = " CREATE TABLE "+TABLE_BORROW_NAME+" ( "+ TABLE_BORROW_ID  +" INTEGER PRIMARY KEY, "+TABLE_USER_ID+" TEXT,"+TABLE_USER_NAME+" TEXT,"+TABLE_BORROW_ISBN_CODE+" TEXT ) ";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_BOOK_CREATE);
        //db.execSQL(TABLE_USER_CREATE);
        db.execSQL(TABLE_BOOK_BORROW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
