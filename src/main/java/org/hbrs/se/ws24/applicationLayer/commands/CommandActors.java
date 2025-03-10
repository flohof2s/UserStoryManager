package org.hbrs.se.ws24.applicationLayer.commands;

import org.hbrs.se.ws24.infrastructureLayer.analyze.exceptions.AnalyzeException;
import org.hbrs.se.ws24.applicationLayer.Container;
import org.hbrs.se.ws24.domainLayer.Actor;

import java.util.List;
import java.util.Map;

public class CommandActors implements Command{
    private Container con;



    public CommandActors(Container con){
        this.con=con;
    }
    @Override
    public void execute(Map<String, String> params) throws AnalyzeException {
        List<Actor> actors = this.con.getActors();
        for(Actor actor : actors){
            System.out.println(actor.getName());
        }
        if(actors.isEmpty()){
            System.out.println("no actors given");
        }
    }

    @Override
    public boolean undo() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Liefert eine Liste aller registrierten Akteure!";
    }
}
