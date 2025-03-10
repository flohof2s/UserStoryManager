package org.hbrs.se.ws24.control;

import org.hbrs.se.ws24.analyze.Analyze;
import org.hbrs.se.ws24.analyze.AnalyzeUserStory;
import org.hbrs.se.ws24.analyze.dto.AnalyzeReturnObject;
import org.hbrs.se.ws24.analyze.exceptions.AnalyzeException;
import org.hbrs.se.ws24.control.commands.Command;
import org.hbrs.se.ws24.control.exceptions.ContainerException;
import org.hbrs.se.ws24.model.Actor;
import org.hbrs.se.ws24.model.UserStory;
import org.hbrs.se.ws24.persistence.PersistenceStrategy;
import org.hbrs.se.ws24.persistence.exceptions.PersistenceException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Container {
    private static final Container instance = new Container();
    private List<UserStory> userStories;
    private List<Actor> actors;
    private PersistenceStrategy p;
    private Analyze<UserStory> analyzeStrategy;

    private Container(){
        this.userStories = new ArrayList<UserStory>();
        this.actors = new ArrayList<>();
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

    public String deleteUserStory(int id) throws ContainerException {

        for(UserStory us : this.userStories){
            if(us.getId()==id){
                this.userStories.remove(us);
                return "Das Member-Objekt mit der ID "+id+" wurde erfolgreich gelöscht!";
            }
        }
        throw new ContainerException("Das Member-Objekt mit der ID "+id+" ist nicht vorhanden und kann nicht gelöscht werden!");
    }


    public boolean containsID(int ID){
        for(UserStory us:this.getCurrentList()){
            if(us.getId()==ID){
                return true;
            }
        }
        return false;
    }

    public UserStory getUserStoryByID(int ID){
        for(UserStory us:this.getCurrentList()){
            if(us.getId()==ID){
                return us;
            }
        }
        return null;
    }
    public List<UserStory> getCurrentList(){
        return this.userStories;
    }

    public boolean containsActor(String name){
        for(Actor actor:this.getActors()){
            if(actor.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    public List<Actor> getActors(){
        return this.actors;
    }
    public void addActor(Actor a){
        this.actors.add(a);
    }
    public void removeActor(Actor a){
        for(Actor actor:this.getActors()){
            if(actor==a){
                this.actors.remove(a);
                break;
            }
        }
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

    public void setAnalyzeStrategy(Analyze<UserStory> a){
        this.analyzeStrategy = a;
    }

    public AnalyzeReturnObject analyze(UserStory us,boolean with_details, boolean with_hints) throws AnalyzeException {
        try{
            return this.analyzeStrategy.analyze(us,Container.getInstance(),with_details,with_hints);
        }catch(UnsupportedOperationException uoe){
            throw new AnalyzeException(AnalyzeException.ExceptionType.ImplementationNotAvailable,"Es ist keine Implementation vorhanden");
        }catch(NullPointerException npe ){
            throw new AnalyzeException(AnalyzeException.ExceptionType.NoStrategyIsSet,"Es wurde keine Persistence Strategie gesetzt");
        }catch(Exception e){
            throw new AnalyzeException(AnalyzeException.ExceptionType.ConnectionNotAvailable,e.getMessage());
        }
    }

    public double analyze() throws AnalyzeException{
        double score = 0.0;
        for(UserStory us : this.getCurrentList()){
            score+=this.analyze(us,false,false).getScore();
        }

        return score / this.size();
    }

    public String getGrade(double score) throws AnalyzeException {
        try{
            return this.analyzeStrategy.getGrade(score);
        }catch(UnsupportedOperationException uoe){
            throw new AnalyzeException(AnalyzeException.ExceptionType.ImplementationNotAvailable,"Es ist keine Implementation vorhanden");
        }catch(NullPointerException npe ){
            throw new AnalyzeException(AnalyzeException.ExceptionType.NoStrategyIsSet,"Es wurde keine Persistence Strategie gesetzt");
        }catch(Exception e){
            throw new AnalyzeException(AnalyzeException.ExceptionType.ConnectionNotAvailable,e.getMessage());
        }
    }
}