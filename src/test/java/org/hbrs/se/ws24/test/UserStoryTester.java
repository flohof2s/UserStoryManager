package org.hbrs.se.ws24.test;

import org.hbrs.se.ws24.applicationLayer.Container;
import org.hbrs.se.ws24.applicationLayer.exceptions.ContainerException;
import org.hbrs.se.ws24.domainLayer.UserStory;
import org.hbrs.se.ws24.infrastructureLayer.persistence.PersistenceStrategy;
import org.hbrs.se.ws24.infrastructureLayer.persistence.PersistenceStrategyStream;
import org.hbrs.se.ws24.infrastructureLayer.persistence.exceptions.PersistenceException;
import org.hbrs.se.ws24.presentationLayer.UserStoryView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserStoryTester {
    public static UserStory us1;
    public static UserStoryView usv;
    public static Container con;
    public static PersistenceStrategy p;

    @BeforeAll
    public static void init(){
        usv = new UserStoryView();
        con = Container.getInstance();
        p = new PersistenceStrategyStream();
        con.setPersistenceStrategy(p);
        try {
            con.store();
        } catch (PersistenceException e) {
            fail(e.getMessage());
        }
    }
    @BeforeEach
    public void setup(){
        try {
            con.setPersistenceStrategy(p);
            con.load();
            con.getCurrentList().clear();
            con.store();
        } catch (PersistenceException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void noStrategySet(){
        con.setPersistenceStrategy(null);
        try{
            con.store();
            fail();
        }catch(PersistenceException e){
            assertEquals(e.getExceptionTypeType(), PersistenceException.ExceptionType.NoStrategyIsSet);
        }

    }

    @Test
    public void wrongFileLocation(){
        try {
            con.store();
        } catch (PersistenceException e) {
            fail();
        }

        PersistenceStrategyStream p2 = new PersistenceStrategyStream();
        p2.setLocation("/objects.ser");
        con.setPersistenceStrategy(p2);

        try{
            con.load();
            fail();
        }catch(PersistenceException e){
            assertEquals(e.getExceptionTypeType(), PersistenceException.ExceptionType.ConnectionNotAvailable);
        }
    }

    @Test
    public void roundTripTest(){
        assertEquals(con.size(),0);
        try{
            con.addUserStory(new UserStory(1,"test1",1,2,3,5,"ok1","","",""));
            assertEquals(con.size(),1);
            con.addUserStory(new UserStory(2,"test2",1,2,3,5,"ok1","","",""));
            assertEquals(con.size(),2);
            con.store();
            assertEquals(con.size(),2);
            con.deleteUserStory(1);
            assertEquals(con.size(),1);
            con.deleteUserStory(2);
            assertEquals(con.size(),0);
            con.load();
            assertEquals(con.size(),2);
        }catch(Exception e){
            fail(e.getMessage());
        }

    }
    @Test
    public void testAddUserStoryWithSameID(){
        try{
            con.addUserStory(new UserStory(1,"test1",1,2,3,5,"ok1","","",""));
            con.addUserStory(new UserStory(2,"test2",1,2,3,5,"ok2","","",""));

        }catch(Exception e){
            fail(e.getMessage());
        }

        ContainerException exception = assertThrows(
                ContainerException.class,
                ()->con.addUserStory(new UserStory(2,"test2",1,2,3,5,"ok2","","",""))
        );

        assertEquals("Das Member-Object mit der ID 2 ist bereits vorhanden!",exception.getMessage());

    }

    @Test
    public void testNullUserStory(){
        assertThrows(
                Exception.class,
                ()->con.addUserStory(null)
        );
    }
}
