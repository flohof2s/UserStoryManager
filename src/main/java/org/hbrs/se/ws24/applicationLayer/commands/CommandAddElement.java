package org.hbrs.se.ws24.applicationLayer.commands;

import org.hbrs.se.ws24.infrastructureLayer.analyze.exceptions.AnalyzeException;
import org.hbrs.se.ws24.applicationLayer.Container;
import org.hbrs.se.ws24.domainLayer.Actor;

import java.util.Map;
import java.util.Stack;

public class CommandAddElement implements Command{
    private Container con;
    private Stack<Actor> addedActors;

    public CommandAddElement(Container con){
        this.con = con;
        this.addedActors = new Stack<>();
    }

    @Override
    public void execute(Map<String, String> params) throws AnalyzeException {
        if(params.containsKey("actor")){
            Actor actor = new Actor(params.get("actor"));
            this.con.addActor(actor);
            this.addedActors.push(actor);
            System.out.println("The actor "+actor.getName()+" was registered in the system!");
        }
    }

    @Override
    public boolean undo() {
        Actor actor = this.addedActors.pop();
        this.con.removeActor(actor);
        return true;
    }

    @Override
    public String getDescription() {
        return "Fügt ein Element hinzu! --actor {Name}: Fügt einen Akteur mit Name hinzu!";
    }
}
