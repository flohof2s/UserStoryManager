package org.hbrs.se.ws24.applicationLayer.commands;

import org.hbrs.se.ws24.infrastructureLayer.analyze.exceptions.AnalyzeException;
import org.hbrs.se.ws24.applicationLayer.exceptions.ContainerException;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public interface Command {
    void execute(Map<String,String> params) throws AnalyzeException;
    boolean undo() throws ContainerException;

    String getDescription();
}
