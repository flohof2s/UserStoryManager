package org.hbrs.se.ws24.test;

import org.hbrs.se.ws24.applicationLayer.Container;
import org.hbrs.se.ws24.applicationLayer.exceptions.ContainerException;
import org.hbrs.se.ws24.domainLayer.UserStory;
import org.hbrs.se.ws24.infrastructureLayer.persistence.PersistenceStrategy;
import org.hbrs.se.ws24.infrastructureLayer.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se.ws24.infrastructureLayer.persistence.PersistenceStrategyStream;
import org.hbrs.se.ws24.infrastructureLayer.persistence.exceptions.PersistenceException;
import org.hbrs.se.ws24.presentationLayer.UserStoryView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PersistenceTester {

    public static UserStory us1,us2;
    public static UserStoryView usv;
    public static Container con;
    public static PersistenceStrategy pStream,pMongo;

    @BeforeAll
    public static void init(){
        us1 = new UserStory();
        us1.setId(1);
        us1.setTitel("testStream");
        us2 = new UserStory();
        us2.setId(2);
        us2.setTitel("testMongo");
        con = Container.getInstance();
        pStream = new PersistenceStrategyStream();
        pMongo = new PersistenceStrategyMongoDB();
    }

    @BeforeEach
    public void setup(){
        con.getCurrentList().clear();
    }

    @Test
    public void testStream(){
        con.setPersistenceStrategy(pStream);
        assertEquals(0,con.size());
        try {
            con.addUserStory(us1);
        } catch (ContainerException e) {
            fail(e.getMessage());
        }
        assertEquals(1,con.size());
        try {
            con.store();
        } catch (PersistenceException e) {
            fail(e.getMessage());
        }
        con.getCurrentList().clear();
        assertEquals(0,con.size());
        try {
            con.load();
        } catch (PersistenceException e) {
            fail(e.getMessage());
        }
        assertEquals(1,con.size());
        assertEquals(1,con.getCurrentList().get(0).getId());
    }

    @Test
    public void testMongo(){
        con.setPersistenceStrategy(pMongo);
        assertEquals(0,con.size());
        try {
            con.addUserStory(us2);
        } catch (ContainerException e) {
            fail(e.getMessage());
        }
        assertEquals(1,con.size());
        try {
            con.store();
        } catch (PersistenceException e) {
            fail(e.getMessage());
        }
        con.getCurrentList().clear();
        assertEquals(0,con.size());
        try {
            con.load();
        } catch (PersistenceException e) {
            fail(e.getMessage());
        }
        assertEquals(1,con.size());
        assertEquals(2,con.getCurrentList().get(0).getId());
    }
}
