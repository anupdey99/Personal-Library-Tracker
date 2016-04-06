package com.adlabsbd.anup.personal_library_tracker.database;

import java.util.ArrayList;

/**
 * Created by Sudarshan Biswas on 20-Mar-16.
 */
public interface UserModelInterface {

    public User getUserDetailsByUserId(String userId);

    public User getUserDetailsStartWithUserId(String userId);

    public ArrayList<User> getAllUsersList();

}
