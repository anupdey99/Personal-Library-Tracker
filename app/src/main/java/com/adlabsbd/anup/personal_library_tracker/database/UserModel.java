package com.adlabsbd.anup.personal_library_tracker.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sudarshan Biswas on 20-Mar-16.
 */
public class UserModel implements UserModelInterface {

    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private User user = new User();
    private ArrayList<User> userArrayList = new ArrayList<User>();

    public UserModel(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }
    public void open(){
        sqLiteDatabase  = databaseHelper.getWritableDatabase();
    }
    public void close(){
        databaseHelper.close();
    }

    @Override
    public User getUserDetailsByUserId(String userId) {
        this.open();
        try {
            Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_BOOK, null, DatabaseHelper.TABLE_USER_ID + " = " + userId, null, null, null, null, null);
            cursor.moveToFirst();
            final String userName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USER_NAME));

            if (!userName.isEmpty()) {
                user.setUserId(userId);
                user.setUserName(userName);
            }

        } catch(Exception e) {
            //Log.d("Error", e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            this.close();
            return user;
        }
    }


    @Override
    public User getUserDetailsStartWithUserId(String userId) {
        return null;
    }

    @Override
    public ArrayList<User> getAllUsersList() {
        this.open();
        try {
            Cursor cursor = sqLiteDatabase.query(true, DatabaseHelper.TABLE_BORROW_NAME, new String[]{DatabaseHelper.TABLE_USER_ID, DatabaseHelper.TABLE_USER_NAME}, null, null, DatabaseHelper.TABLE_USER_ID, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        String userId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USER_ID));
                        String userName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_USER_NAME));
                        userArrayList.add(new User(userName, userId));
                    } while (cursor.moveToNext());
                }
            }
        } catch(SQLiteException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            this.close();
            return userArrayList;
        }
    }

}
