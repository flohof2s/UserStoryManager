package org.hbrs.se.ws24.control;

import org.hbrs.se.ws24.control.exceptions.ContainerException;
import org.hbrs.se.ws24.model.UserStory;
import org.hbrs.se.ws24.persistence.PersistenceStrategy;
import org.hbrs.se.ws24.persistence.exceptions.PersistenceException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Container {
    private static final Container instance = new Container();
    private List<UserStory> userStories;
    private PersistenceStrategy p;

    private Container(){
        this.userStories = new ArrayList<UserStory>();
        Collections.sort(this.userStories);
    }

    public static Container getInstance(){
        return instance;
    }

    public void addUserStory(UserStory userStory) throws ContainerException,NullPointerException {
        if(userStory==null){
            throw new NullPointerException();
        }
        for(UserStory us : this.userStories){
            if(userStory.getId() == us.getId()){
                throw new ContainerException(userStory.getId());
            }
        }

        this.userStories.add(userStory);
    }

    public String deleteUserStory(Integer id) throws ContainerException {

        for(UserStory us : this.userStories){
            if(us.getId()==id){
                this.userStories.remove(us);
                return "Das Member-Objekt mit der ID "+id+" wurde erfolgreich gelöscht!";
            }
        }
        throw new ContainerException("Das Member-Objekt mit der ID "+id+" ist nicht vorhanden und kann nicht gelöscht werden!");
    }



    public List<UserStory> getCurrentList(){
        return this.userStories;
    }

    public int size(){
        return this.userStories.size();
    }

    public void store() throws PersistenceException {
        try{
            this.p.save(this.userStories);
        }catch(UnsupportedOperationException uoe){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,"Es ist keine Implementation vorhanden");
        }catch(NullPointerException npe ){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"Es wurde keine Persistence Strategie gesetzt");
        }catch(Exception e){
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,e.getMessage());
        }

    }

    public void load() throws PersistenceException {
        try{
            this.userStories = this.p.load();
        }catch(UnsupportedOperationException uoe){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,"Es ist keine Implementation vorhanden");
        }catch(NullPointerException npe ){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"Es wurde keine Persistence Strategie gesetzt");
        }catch(Exception e){
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,e.getMessage());
        }

    }

    public void setPersistenceStrategy(PersistenceStrategy p){
        this.p = p;
    }
}