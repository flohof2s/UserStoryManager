package org.hbrs.se.ws24.applicationLayer.commands;

import org.hbrs.se.ws24.applicationLayer.Container;
import org.hbrs.se.ws24.presentationLayer.UserStoryView;

import java.util.Map;

public class CommandDump implements Command{

    private Container con;
    public CommandDump(Container con){
        this.con = con;
    }


    @Override
    public void execute(Map<String,String> params) {
        if(params.containsKey("projekt")){
            if(params.get("projekt").equals("")){
                System.out.println("Der angegebene Befehl ist unvollständig! Sie müssen das Projekt spezifizieren!");
            }else{
                UserStoryView.dump(con.getCurrentList(),params.get("projekt"));
            }
        }else{
            UserStoryView.dump(con.getCurrentList(),"");
        }
    }

    @Override
    public boolean undo() {
        return false;
    }

    @Override
    public String getDescription() {
        return "( projekt PROJEKTNAME): Gibt alle User Stories aus. Ist kein Parameter angegeben, erfolgt die Ausgabe nach Priorisierung, sonst nach Projekt";
    }
}
