package com.adlabsbd.anup.personal_library_tracker.book_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ListView;

import com.adlabsbd.anup.personal_library_tracker.database.Book;
import com.adlabsbd.anup.personal_library_tracker.database.Library;
import com.adlabsbd.anup.personal_library_tracker.R;

import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {

    ListView bookListView;

    BookListAdapter adapter;
    Library library;
    ArrayList<Book> bookList = new ArrayList();
    ImageButton editBookIBID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

        bookListView = (ListView) findViewById(R.id.bookListViewID);
        this.setListView();    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        this.setListView();
    }

    private void setListView() {
        library = new Library(BookListActivity.this);

        bookList = library.getAllBookList();

        adapter = new BookListAdapter(BookListActivity.this, bookList);
        adapter.notifyDataSetChanged();
        bookListView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

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
