package org.hbrs.se.ws24.applicationLayer.commands;

import org.hbrs.se.ws24.applicationLayer.Container;
import org.hbrs.se.ws24.infrastructureLayer.persistence.exceptions.PersistenceException;

import java.util.Map;

public class CommandStore implements Command{

    private Container con;

    public CommandStore(Container con){
        this.con=con;
    }

    @Override
    public void execute(Map<String,String> params) {
        try {
            this.con.store();
        } catch (PersistenceException e) {
            System.out.println("Es ist etwas schief gelaufen! Fehler: "+e.getMessage());
        }
    }

    @Override
    public boolean undo() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Speichert alle User Stories in den Speicher";
    }
}
