package org.hbrs.se.ws24.control.commands;

import java.util.Map;

public class CommandHelp implements Command{
    private final Map<String ,String> descriptions;
    public CommandHelp(Map<String ,String> descriptions){
        this.descriptions = descriptions;
    }
    @Override
    public void execute(Map<String, String> params) {
        this.descriptions.forEach((k,v) -> {System.out.println(k+": "+v);});
    }

    @Override
    public boolean undo() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Listet alle verfügbaren Befehle";
    }
}
