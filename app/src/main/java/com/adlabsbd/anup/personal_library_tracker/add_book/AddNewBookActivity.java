package com.adlabsbd.anup.personal_library_tracker.add_book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.adlabsbd.anup.personal_library_tracker.database.Book;
import com.adlabsbd.anup.personal_library_tracker.database.Library;
import com.adlabsbd.anup.personal_library_tracker.R;
import com.adlabsbd.anup.personal_library_tracker.book_list.BookListActivity;

public class AddNewBookActivity extends AppCompatActivity {

    EditText isbnCodeEditText, bookNameEditText, authorNameEditText, bookQuantityEditText, bookEditionEditText, bookLocationEditText, bookPublisherEditText;
    Spinner bookCategorySpinner;
    String isbnCodeString, bookNameString, authorNameString, bookQuantityString, bookEditionString, bookLocationString, bookPublisherString, bookCategoryString;
    Button addNewBookSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

        initAllFormField();


        addNewBookSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();

                isbnCodeString = isbnCodeEditText.getText().toString();
                bookNameString = bookNameEditText.getText().toString();
                authorNameString = authorNameEditText.getText().toString();
                bookCategoryString = bookCategorySpinner.getSelectedItem().toString();
                bookQuantityString = bookQuantityEditText.getText().toString();
                bookEditionString = bookEditionEditText.getText().toString();
                bookLocationString = bookLocationEditText.getText().toString();
                bookPublisherString = bookPublisherEditText.getText().toString();

                try {
                    if (isbnCodeString.isEmpty()) {
                        Toast.makeText(AddNewBookActivity.this, "You must provide ISBN code number of book.", Toast.LENGTH_LONG).show();
                        return;
                    } else if (bookNameString.isEmpty()) {
                        Toast.makeText(AddNewBookActivity.this, "You must provide book name.", Toast.LENGTH_LONG).show();
                        return;
                    } else if (authorNameString.isEmpty()) {
                        Toast.makeText(AddNewBookActivity.this, "You must provide author name.", Toast.LENGTH_LONG).show();
                        return;
                    } else if (bookQuantityString.isEmpty() || Integer.parseInt(bookQuantityString) < 0) {
                        Toast.makeText(AddNewBookActivity.this, "You must provide book quantity at least 1.", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        book.setBookISBN(isbnCodeString);
                        book.setBookName(bookNameString);
                        book.setBookAuthor(authorNameString);
                        book.setBookType(bookCategoryString);
                        book.setBookEdition(bookEditionString);
                        book.setBookLocation(bookLocationString);
                        book.setBookPublisher(bookPublisherString);
                        book.setBookQuantity(Integer.parseInt(bookQuantityString));

                        Library library = new Library(getApplicationContext());
                        try {
                            if (library.addBookToLibrary(book)) {
                                clearAllField();
                                Toast.makeText(AddNewBookActivity.this, "Added new book", Toast.LENGTH_SHORT).show();
                            } else {
                                throw new RuntimeException();
                            }
                        } catch (Exception e) {
                            Toast.makeText(AddNewBookActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(AddNewBookActivity.this, "Unexpected exception is happened. Please fill the form correctly.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void initAllFormField() {
        isbnCodeEditText = (EditText) findViewById(R.id.isbnCodeEditText);
        bookNameEditText = (EditText) findViewById(R.id.bookNameEditText);
        authorNameEditText = (EditText) findViewById(R.id.authorNameEditText);
        bookQuantityEditText = (EditText) findViewById(R.id.bookQuantityEditText);
        bookEditionEditText = (EditText) findViewById(R.id.bookEditionEditText);
        bookLocationEditText = (EditText) findViewById(R.id.bookLocationEditText);
        bookPublisherEditText = (EditText) findViewById(R.id.bookPublisherEditText);

        bookCategorySpinner = (Spinner) findViewById(R.id.bookCategorySpinner);

        addNewBookSubmitButton = (Button) findViewById(R.id.addNewBookSubmitButton);
    }

    private void clearAllField() {
        isbnCodeEditText.getText().clear();
        bookNameEditText.getText().clear();
        authorNameEditText.getText().clear();
        bookQuantityEditText.getText().clear();
        bookEditionEditText.getText().clear();
        bookLocationEditText.getText().clear();
        bookPublisherEditText.getText().clear();
        bookCategorySpinner.setSelection(0);

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
                Intent intent = new Intent(AddNewBookActivity.this, BookListActivity.class);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}