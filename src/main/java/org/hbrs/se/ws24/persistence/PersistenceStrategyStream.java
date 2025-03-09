package org.hbrs.se.ws24.persistence;

import org.hbrs.se.ws24.persistence.exceptions.PersistenceException;
import org.hbrs.se.ws24.model.UserStory;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream implements PersistenceStrategy {

    // URL of file, in which the objects are stored
    private String location = "objects.ser";

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void save(List<UserStory> member) throws PersistenceException {
        try {
            FileOutputStream fos = new FileOutputStream(this.location);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // write object to file
            oos.writeObject(member);
            // closing resources
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,e.getMessage());
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<UserStory> load() throws PersistenceException  {
        // Some Coding hints ;-)

        // ObjectInputStream ois = null;
        // FileInputStream fis = null;
        // List<...> newListe =  null;
        //
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        // fis = new FileInputStream( " a location to a file" );

        // Tipp: Use a directory (ends with "/") to implement a negative test case ;-)
        // ois = new ObjectInputStream(fis);

        // Reading and extracting the list (try .. catch ommitted here)
        // Object obj = ois.readObject();

        // if (obj instanceof List<?>) {
        //       newListe = (List) obj;
        // return newListe

        // and finally close the streams
        List<UserStory> list = null;
        try{
            FileInputStream is = new FileInputStream(this.location);
            ObjectInputStream ois = new ObjectInputStream(is);
            Object obj = ois.readObject();
            if(obj instanceof List<?>){
                list = (List) obj;
            }
            ois.close();
            is.close();
        }catch(Exception e){
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,e.getMessage());
        }

        return list;

    }
}
