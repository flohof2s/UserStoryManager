package org.hbrs.se.ws24.persistence;

import org.hbrs.se.ws24.model.UserStory;

import java.util.ArrayList;
import java.util.List;

public interface MongoDBControllerInterface {
        void openConnection();
        void closeConnection();

        void insertUserStory(UserStory story);
        void updateUserStory(int id, UserStory story);
        void deleteUserStory(int id);
        void clearUserStories();
        UserStory readUserStory(int id);
        List<UserStory> listUserStories();
 }