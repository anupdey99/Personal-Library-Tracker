package com.adlabsbd.anup.personal_library_tracker.borrow_book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Filter;

import com.adlabsbd.anup.personal_library_tracker.database.Book;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Sudarshan Biswas on 20-Mar-16.
 */
public class BorrowBookListAdapter extends ArrayAdapter<Book> {

    private int resource, textViewResourceId;
    private Context context;
    private ArrayList<Book> bookArrayList;
    private ArrayList<Book> filterBookArrayList = new ArrayList<Book>();
    private Book book;
    private BookFilter isbnCodeFilter;

    public BorrowBookListAdapter(Context context, int resource, int textViewResourceId, ArrayList<Book> bookArrayList) {
        super(context, resource, textViewResourceId, bookArrayList);
        this.context = context;
        this.resource = resource;
        this.bookArrayList = bookArrayList;
        this.textViewResourceId = textViewResourceId;
        cloneItems(bookArrayList);
    }

    protected void cloneItems(ArrayList<Book> items) {
        for (Iterator iterator = items.iterator(); iterator.hasNext();) {
            Book bookItem = (Book) iterator.next();
            filterBookArrayList.add(bookItem);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if ( convertView == null ) {
            view = LayoutInflater.from(context).inflate(android.R.layout.select_dialog_item, null);
            //viewHolder.personNameTextView = (TextView) convertView.findViewById(R.id.personNameTextView);
        }
        book = bookArrayList.get(position);
        if (book != null) {
            TextView isbnCodeTextView = (TextView) view.findViewById(textViewResourceId);

            if (isbnCodeTextView != null) {
                isbnCodeTextView.setText(book.getBookISBN());
            }
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        if (isbnCodeFilter == null) {
            isbnCodeFilter = new BookFilter();
        }
        return isbnCodeFilter;
    }

    private class BookFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                filterResults.values = filterBookArrayList;
                filterResults.count = filterBookArrayList.size();
            } else {
                String constraintString = constraint.toString().toLowerCase();
                final ArrayList<Book> filteredBookItems = new ArrayList<Book>();
                final ArrayList<Book> localBookItems = new ArrayList<Book>();
                localBookItems.addAll(filterBookArrayList);
                final int count = localBookItems.size();

                for (int i = 0; i < count; i++) {
                    final Book bookItem = localBookItems.get(i);
                    final String isbnCode = bookItem.getBookISBN().toString().toLowerCase();

                    if (isbnCode.contains(constraintString)) {
                        filteredBookItems.add(bookItem);
                    } else {}
                }

                filterResults.values = filteredBookItems;
                filterResults.count = filteredBookItems.size();
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            final ArrayList<Book> localBookItems = (ArrayList<Book>) filterResults.values;
            notifyDataSetChanged();
            clear();
            for (Iterator iterator = localBookItems.iterator(); iterator.hasNext();) {
                Book bookItem = (Book) iterator.next();
                add(bookItem);
            }
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Book) resultValue).getBookISBN();
        }
    }
}
