package org.hbrs.se.ws24.infrastructureLayer.persistence;

import org.hbrs.se.ws24.domainLayer.UserStory;

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