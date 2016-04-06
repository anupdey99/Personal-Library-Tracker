package com.adlabsbd.anup.personal_library_tracker.return_book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.adlabsbd.anup.personal_library_tracker.book_list.BookListActivity;
import com.adlabsbd.anup.personal_library_tracker.database.Book;
import com.adlabsbd.anup.personal_library_tracker.database.Library;
import com.adlabsbd.anup.personal_library_tracker.R;
import com.adlabsbd.anup.personal_library_tracker.database.User;
import com.adlabsbd.anup.personal_library_tracker.database.UserListAdapter;
import com.adlabsbd.anup.personal_library_tracker.database.UserModel;
import com.adlabsbd.anup.personal_library_tracker.borrow_book.BorrowBookListAdapter;

import java.util.ArrayList;

public class ReturnBookActivity extends AppCompatActivity {

    AutoCompleteTextView userIdAutoCompleteTextView, isbnCodeAutoCompleteTextView;
    Button returnBookSubmitButton;
    String userIdString, isbnCodeString;
    ArrayList<Book> bookArrayList;
    ArrayAdapter<Book> bookArrayAdapter;
    ArrayList<User> userArrayList;
    ArrayAdapter<User> userArrayAdapter;
    Book book;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_book);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

        initAllFormField();

        final UserModel userModel = new UserModel(ReturnBookActivity.this);
        final Library library = new Library(ReturnBookActivity.this);

        userArrayList = userModel.getAllUsersList();
        userArrayAdapter = new UserListAdapter(ReturnBookActivity.this, R.layout.activity_borrow_book, android.R.id.text1, userArrayList);
        userIdAutoCompleteTextView.setAdapter(userArrayAdapter);
        userIdAutoCompleteTextView.setThreshold(1);

        /*userIdAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user = userArrayList.get(position);
                userNameEditText.setText(user.getUserName());
            }
        });*/

        bookArrayList = library.getAllBookList();
        bookArrayAdapter = new BorrowBookListAdapter(ReturnBookActivity.this, R.layout.activity_borrow_book, android.R.id.text1, bookArrayList);
        isbnCodeAutoCompleteTextView.setAdapter(bookArrayAdapter);
        isbnCodeAutoCompleteTextView.setThreshold(1);

        returnBookSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userIdString = userIdAutoCompleteTextView.getText().toString();
                isbnCodeString = isbnCodeAutoCompleteTextView.getText().toString();

                try {
                    if (userIdString.isEmpty()) {
                        throw new Exception("User ID is required.");
                    } else if (isbnCodeString.isEmpty()) {
                        throw new Exception("Book ISBN code is required.");
                    } else {
                        try {
                            if (!library.returnBook(isbnCodeString, userIdString)) {
                                throw new Exception("Returning book fail.");
                            } else {
                                userIdAutoCompleteTextView.getText().clear();
                                isbnCodeAutoCompleteTextView.getText().clear();
                                Toast.makeText(ReturnBookActivity.this, "Book borrowed.", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Log.d("Error: ", e.getMessage());
                            Toast.makeText(ReturnBookActivity.this, "Unexpected exception is happened. For details show log file.", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(ReturnBookActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void initAllFormField() {
        userIdAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.userIdAutoCompleteTextView);
        //userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        isbnCodeAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.isbnCodeAutoCompleteTextView);
        //bookNameTextView = (TextView) findViewById(R.id.bookNameTextView);

        returnBookSubmitButton = (Button) findViewById(R.id.returnBookSubmitButton);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuBookListID:
                Intent intent = new Intent(ReturnBookActivity.this, BookListActivity.class);
                startActivity(intent);
                finish();
                return true;
            case android.R.id.home:

                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
