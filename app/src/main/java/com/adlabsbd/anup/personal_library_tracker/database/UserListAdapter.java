package com.adlabsbd.anup.personal_library_tracker.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Sudarshan Biswas on 21-Mar-16.
 */
public class UserListAdapter extends ArrayAdapter<User> {

    private int textViewResourceId;
    private Context context;
    private ArrayList<User> userArrayList;
    private ArrayList<User> filterUserArrayList = new ArrayList<User>();
    private User user;
    private UserFilter userIdFilter;

    public UserListAdapter(Context context, int resource, int textViewResourceId, ArrayList<User> userArrayList) {
        super(context, resource, textViewResourceId, userArrayList);
        this.context = context;
        this.textViewResourceId = textViewResourceId;
        this.userArrayList = userArrayList;
        cloneItems(userArrayList);
    }

    protected void cloneItems(ArrayList<User> items) {
        for (Iterator iterator = items.iterator(); iterator.hasNext();) {
            User singleUser = (User) iterator.next();
            filterUserArrayList.add(singleUser);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(android.R.layout.select_dialog_item, null);
        }

        user = userArrayList.get(position);
        if (user != null) {
            TextView userIdTextView = (TextView) view.findViewById(textViewResourceId);

            if (userIdTextView != null) {
                userIdTextView.setText(user.getUserId());
            }

        }
        return view;
    }

    @Override
    public Filter getFilter() {
        if (userIdFilter == null) {
            userIdFilter = new UserFilter();
        }
        return userIdFilter;
    }

    private class UserFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                filterResults.values = filterUserArrayList;
                filterResults.count = filterUserArrayList.size();
            } else {
                String constraintString = constraint.toString().toLowerCase();
                final ArrayList<User> filteredUserList = new ArrayList<User>();
                final ArrayList<User> localUserList = new ArrayList<User>();
                localUserList.addAll(filterUserArrayList);
                final int count = localUserList.size();

                for (int i = 0; i < count; i++) {
                    final User singleUser = localUserList.get(i);
                    final String userId = singleUser.getUserId().toString().toLowerCase();

                    if (userId.contains(constraintString)) {
                        filteredUserList.add(singleUser);
                    } else {}
                }

                filterResults.values = filteredUserList;
                filterResults.count = filteredUserList.size();
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            final ArrayList<User> localUserList = (ArrayList<User>) filterResults.values;
            notifyDataSetChanged();
            clear();
            for (Iterator iterator = localUserList.iterator(); iterator.hasNext();) {
                User singleUser = (User) iterator.next();
                add(singleUser);
            }
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((User) resultValue).getUserId();
        }
    }

}
