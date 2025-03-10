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

    default int readLine(String message, Scanner sc, int min, int max){
        System.out.println(message);
        try{
            int result = sc.nextInt();
            if(result<min || result>max){
                System.out.println("Bitte geben Sie einen Wert zwischen "+min+" und "+max+" ein!");
                return this.readLine(message,sc,min,max);
            }
            return result;
        } catch (InputMismatchException e){
            System.out.println("Sie müssen eine gültige Zahl als Eingabe wählen!");
            sc.nextLine();
            return this.readLine(message,sc,min,max);
        } catch (Exception e){
            System.out.println("Es ist ein Fehler aufgetreten: "+e.getMessage());
            sc.nextLine();
            return this.readLine(message,sc,min,max);
        }
    }
    default String readLine(String message,Scanner sc){
        System.out.println(message);
        try{
            return sc.nextLine();
        }catch(Exception e){
            System.out.println("Es ist ein Fehler aufgetreten: "+e.getMessage());
            sc.nextLine();
            return this.readLine(message,sc);
        }
    }
}
