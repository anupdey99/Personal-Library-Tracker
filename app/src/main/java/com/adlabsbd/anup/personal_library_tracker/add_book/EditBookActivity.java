package com.adlabsbd.anup.personal_library_tracker.add_book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.adlabsbd.anup.personal_library_tracker.database.Book;
import com.adlabsbd.anup.personal_library_tracker.database.Library;
import com.adlabsbd.anup.personal_library_tracker.R;
import com.adlabsbd.anup.personal_library_tracker.book_list.BookListActivity;

import java.util.Arrays;

public class EditBookActivity extends AppCompatActivity {

    EditText isbnCodeEditText, bookNameEditText, authorNameEditText, bookQuantityEditText, bookEditionEditText, bookLocationEditText, bookPublisherEditText;
    Spinner bookCategorySpinner;
    String isbnCodeString, bookNameString, authorNameString, bookQuantityString, bookEditionString, bookLocationString, bookPublisherString, bookCategoryString;
    int bookIdInt;
    Button updateBookInfoButton;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        initAllFormField();

        final String isbnCode = getIntent().getExtras().getString("book_id");
        final Library library = new Library(EditBookActivity.this);
        book = library.getBookDetails(isbnCode);

        isbnCodeEditText.setText(book.getBookISBN());
        bookNameEditText.setText(book.getBookName());
        authorNameEditText.setText(book.getBookAuthor());
        bookEditionEditText.setText(book.getBookEdition());
        bookLocationEditText.setText(book.getBookLocation());
        bookPublisherEditText.setText(book.getBookPublisher());
        String[] bookCategory = getResources().getStringArray(R.array.book_type_list);
        bookCategorySpinner.setSelection(Arrays.asList(bookCategory).indexOf(book.getBookType()));

        updateBookInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book updateBook = new Book();
                isbnCodeString = isbnCodeEditText.getText().toString();
                bookNameString = bookNameEditText.getText().toString();
                authorNameString = authorNameEditText.getText().toString();
                bookCategoryString = bookCategorySpinner.getSelectedItem().toString();
                bookEditionString = bookEditionEditText.getText().toString();
                bookLocationString = bookLocationEditText.getText().toString();
                bookPublisherString = bookPublisherEditText.getText().toString();

                try {
                    if (isbnCodeString.isEmpty()) {
                        Toast.makeText(EditBookActivity.this, "You must provide ISBN code number of book.", Toast.LENGTH_LONG).show();
                        return;
                    } else if (bookNameString.isEmpty()) {
                        Toast.makeText(EditBookActivity.this, "You must provide book name.", Toast.LENGTH_LONG).show();
                        return;
                    } else if (authorNameString.isEmpty()) {
                        Toast.makeText(EditBookActivity.this, "You must provide author name.", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        updateBook.setBookISBN(isbnCodeString);
                        updateBook.setBookName(bookNameString);
                        updateBook.setBookAuthor(authorNameString);
                        updateBook.setBookType(bookCategoryString);
                        updateBook.setBookEdition(bookEditionString);
                        updateBook.setBookLocation(bookLocationString);
                        updateBook.setBookPublisher(bookPublisherString);

                        try {
                            if (library.updateBook(updateBook, book.getBookId())) {
                                clearAllField();
                                Toast.makeText(EditBookActivity.this, "Update book info.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EditBookActivity.this, BookListActivity.class);
                                startActivity(intent);
                            } else {
                                throw new RuntimeException();
                            }
                        } catch (Exception e) {
                            Toast.makeText(EditBookActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(EditBookActivity.this, "Unexpected exception is happened. Please fill the form correctly.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    private void initAllFormField() {
        isbnCodeEditText = (EditText) findViewById(R.id.isbnCodeEditText);
        bookNameEditText = (EditText) findViewById(R.id.bookNameEditText);
        authorNameEditText = (EditText) findViewById(R.id.authorNameEditText);
        bookEditionEditText = (EditText) findViewById(R.id.bookEditionEditText);
        bookLocationEditText = (EditText) findViewById(R.id.bookLocationEditText);
        bookPublisherEditText = (EditText) findViewById(R.id.bookPublisherEditText);
        bookCategorySpinner = (Spinner) findViewById(R.id.bookCategorySpinner);
        updateBookInfoButton = (Button) findViewById(R.id.updateBookInfoButton);
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
}
