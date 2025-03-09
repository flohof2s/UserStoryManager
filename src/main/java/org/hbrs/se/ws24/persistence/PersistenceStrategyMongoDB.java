package org.hbrs.se.ws24.persistence;

import org.hbrs.se.ws24.persistence.exceptions.PersistenceException;
import org.hbrs.se.ws24.model.UserStory;

import java.util.List;

public class PersistenceStrategyMongoDB implements PersistenceStrategy {
    MongoDBControllerImpl mongoDBController;

    public PersistenceStrategyMongoDB(){
        this.mongoDBController = new MongoDBControllerImpl();
    }

    @Override
    public void save(List<UserStory> member) throws PersistenceException {

    }

    @Override
    public List<UserStory> load() throws PersistenceException {
        this.mongoDBController.openConnection();

        List<UserStory> returnList = mongoDBController.listUserStories();

        this.mongoDBController.closeConnection();
        return returnList;
    }
}
