package org.hbrs.se.ws24.persistence;

import org.hbrs.se.ws24.persistence.exceptions.PersistenceException;

import java.util.List;
import org.hbrs.se.ws24.model.UserStory;

public interface PersistenceStrategy {
    public void save(List<UserStory> member) throws PersistenceException;
    public List<UserStory> load() throws PersistenceException;
}
