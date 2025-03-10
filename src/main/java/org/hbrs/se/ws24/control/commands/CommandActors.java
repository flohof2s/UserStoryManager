package org.hbrs.se.ws24.control.commands;

import org.hbrs.se.ws24.analyze.exceptions.AnalyzeException;
import org.hbrs.se.ws24.control.Container;
import org.hbrs.se.ws24.model.Actor;

import java.util.List;
import java.util.Map;
import java.util.Stack;

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
        return null;
    }
}
