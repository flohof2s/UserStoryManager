package org.hbrs.se.ws24.control.commands;

import org.hbrs.se.ws24.control.Container;
import org.hbrs.se.ws24.persistence.exceptions.PersistenceException;

import java.util.Map;

public class CommandLoad implements Command {
    private Container con;

    public CommandLoad(Container con){
        this.con = con;
    }

    @Override
    public void execute(Map<String, String> params) {
        try {
            this.con.load();
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
        return "Lädt alle User Stories aus dem Speicher";
    }
}
