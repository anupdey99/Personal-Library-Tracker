package com.adlabsbd.anup.personal_library_tracker.book_list;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adlabsbd.anup.personal_library_tracker.database.Book;
import com.adlabsbd.anup.personal_library_tracker.add_book.EditBookActivity;
import com.adlabsbd.anup.personal_library_tracker.database.Library;
import com.adlabsbd.anup.personal_library_tracker.R;

import java.util.ArrayList;

/**
 * Created by Anup on 17-Mar-16.
 */
public class BookListAdapter extends ArrayAdapter {


    Context context;
    ArrayList<Book> bookList;
    Library library;
    Book book;

    public BookListAdapter(Context context, ArrayList<Book> objects) {
        super(context, R.layout.book_list_row, objects);
        this.context = context;
        this.bookList = objects;

    }

    static class ViewHolder {

        ImageView bookCoverIV;
        TextView bookNameTV;
        TextView bookCategoryTV;
        TextView bookQtyTV;
        ImageButton editBookIB;
        ImageButton deleteBookIB;
        ImageButton detailBookInfoIB;
        ImageButton addBookQtyIB;
        ImageButton borrowBookIB;

    }

    // generate view by myself
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            // to convert my xml custom layout into view!
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.book_list_row, null);

            viewHolder = new ViewHolder();
            viewHolder.bookCoverIV = (ImageView) convertView.findViewById(R.id.bookCoverIVID);
            viewHolder.bookNameTV = (TextView) convertView.findViewById(R.id.bookNameTVID);
            viewHolder.bookCategoryTV = (TextView) convertView.findViewById(R.id.bookCategoryTVID);
            viewHolder.bookQtyTV = (TextView) convertView.findViewById(R.id.bookQtyTVID);
            viewHolder.editBookIB = (ImageButton) convertView.findViewById(R.id.editBookIBID);
            viewHolder.deleteBookIB = (ImageButton) convertView.findViewById(R.id.deleteBookIBID);
            viewHolder.detailBookInfoIB = (ImageButton) convertView.findViewById(R.id.detailBookInfoIBID);
            viewHolder.addBookQtyIB = (ImageButton) convertView.findViewById(R.id.addBookQtyIBID);
            viewHolder.borrowBookIB = (ImageButton) convertView.findViewById(R.id.borrowBookIBID);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }


        viewHolder.bookCoverIV.setImageResource(R.drawable.open_book);
        viewHolder.bookNameTV.setText(bookList.get(position).getBookName());
        viewHolder.bookCategoryTV.setText(bookList.get(position).getBookType());
        viewHolder.bookQtyTV.setText(String.valueOf(bookList.get(position).getBookQuantity()));


        // Add Quantity Btn Task
        viewHolder.addBookQtyIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_dialog_addbook);
                dialog.setTitle("Add More Book");

                // set the custom dialog components - text, image and button
                final EditText newQuantityET = (EditText) dialog.findViewById(R.id.newQuantityETID);
                Button cancelBtn = (Button) dialog.findViewById(R.id.addBookBtnCancel);
                Button doneBtn = (Button) dialog.findViewById(R.id.addBookBtnDone);

                // if Cancel button is clicked, close the custom dialog
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                // if Done Btn is Clicked

                doneBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int quantity = 0;
                        String newQuantity = newQuantityET.getText().toString();
                        if (newQuantity == null) {
                            Toast.makeText(context, "Field is Empty", Toast.LENGTH_SHORT).show();
                        } else {


                            try {
                                quantity = Integer.parseInt(newQuantity);
                            } catch (NumberFormatException e) {

                            }
                            if (quantity > 0) {
                                library = new Library(context);
                                if (library.addBookQuantity(bookList.get(position).getBookId(), quantity)) {
                                    Toast.makeText(context, "New Books Added!", Toast.LENGTH_SHORT).show();
                                }

                            }


                        }
                        dialog.dismiss();

                    }

                });

                dialog.show();

            }
        });

        // Delete Btn Task
        viewHolder.deleteBookIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Alert Dialog Builder
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("Remove Book");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Do you want to Remove this book from Library?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                library = new Library(context);
                                if (library.deleteBookFromLibrary(bookList.get(position).getBookId())) {
                                    Toast.makeText(getContext(), "Book Removed", Toast.LENGTH_SHORT).show();
                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


            }
        });

        // Detail Btn Task
        viewHolder.detailBookInfoIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_dialog_details);
                dialog.setTitle("Book Details");

                // set the custom dialog components - text, image and button

                TextView isbnTV = (TextView) dialog.findViewById(R.id.bookDetailsISBNTVID);
                TextView bookNameTV = (TextView) dialog.findViewById(R.id.bookDetailsBookNameTVID);
                TextView authorNameTV = (TextView) dialog.findViewById(R.id.bookDetailsAuthorTVID);
                TextView editionTV = (TextView) dialog.findViewById(R.id.bookDetailsEditionTVID);
                TextView typeTV = (TextView) dialog.findViewById(R.id.bookDetailsTypeTVID);
                TextView publisherTV = (TextView) dialog.findViewById(R.id.bookDetailsPublisherTVID);
                TextView locationTV = (TextView) dialog.findViewById(R.id.bookDetailsLocationTVID);
                TextView quantityTV = (TextView) dialog.findViewById(R.id.bookDetailsQuantityTVID);
                Button doneBtn = (Button) dialog.findViewById(R.id.bookDetailsBtnID);

                // Set Values from DB
                isbnTV.setText(bookList.get(position).getBookISBN());
                bookNameTV.setText(bookList.get(position).getBookName());
                authorNameTV.setText(bookList.get(position).getBookAuthor());
                editionTV.setText(bookList.get(position).getBookEdition());
                typeTV.setText(bookList.get(position).getBookType());
                publisherTV.setText(bookList.get(position).getBookPublisher());
                locationTV.setText(bookList.get(position).getBookLocation());
                quantityTV.setText(String.valueOf(bookList.get(position).getBookQuantity()));

                // if Done Btn is Clicked

                doneBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();

                    }
                });

                dialog.show();


            }
        });

        // ToDo
//        viewHolder.editBookIB
//        viewHolder.borrowBookIB

        viewHolder.editBookIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book singleBook = bookList.get(position);
                Intent listIntent = new Intent(v.getContext(), EditBookActivity.class);
                listIntent.putExtra("book_id", singleBook.getBookISBN());
                v.getContext().startActivity(listIntent);
            }
        });


        return convertView;
    }
}
