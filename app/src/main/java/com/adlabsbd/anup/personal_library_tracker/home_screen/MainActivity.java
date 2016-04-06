package com.adlabsbd.anup.personal_library_tracker.home_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.adlabsbd.anup.personal_library_tracker.about_app.AboutActivity;
import com.adlabsbd.anup.personal_library_tracker.add_book.AddNewBookActivity;
import com.adlabsbd.anup.personal_library_tracker.book_list.BookListActivity;
import com.adlabsbd.anup.personal_library_tracker.borrow_book.BorrowBookActivity;
import com.adlabsbd.anup.personal_library_tracker.R;
import com.adlabsbd.anup.personal_library_tracker.return_book.ReturnBookActivity;

public class MainActivity extends AppCompatActivity {

    Button addNewBookButton, borrowBookButton, returnBookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addNewBookButton = (Button) findViewById(R.id.addNewBookButton);
        borrowBookButton = (Button) findViewById(R.id.borrowBookButton);
        returnBookButton = (Button) findViewById(R.id.returnBookButton);

        addNewBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewBookActivity.class);
                startActivity(intent);
            }
        });

        borrowBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BorrowBookActivity.class);
                startActivity(intent);
            }
        });

        returnBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReturnBookActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                Intent intent = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void goToBookList(View view){

        Intent intent = new Intent(MainActivity.this,BookListActivity.class);
        startActivity(intent);
    }

}
