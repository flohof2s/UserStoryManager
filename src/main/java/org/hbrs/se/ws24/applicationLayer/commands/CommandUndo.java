package org.hbrs.se.ws24.applicationLayer.commands;

import org.hbrs.se.ws24.infrastructureLayer.analyze.exceptions.AnalyzeException;

import java.util.Map;

public class CommandUndo implements Command{

    @Override
    public void execute(Map<String, String> params) throws AnalyzeException {

    }

    @Override
    public boolean undo() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Undos either the last `addElement` or `enter` command";
    }
}
