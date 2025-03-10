package org.hbrs.se.ws24.infrastructureLayer.persistence;

import org.hbrs.se.ws24.infrastructureLayer.persistence.exceptions.PersistenceException;

import java.util.List;
import org.hbrs.se.ws24.domainLayer.UserStory;

public interface PersistenceStrategy {
    public void save(List<UserStory> member) throws PersistenceException;
    public List<UserStory> load() throws PersistenceException;
}
