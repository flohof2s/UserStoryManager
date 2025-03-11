package org.hbrs.se.ws24.applicationLayer.commands;

import org.hbrs.se.ws24.applicationLayer.Container;
import org.hbrs.se.ws24.applicationLayer.exceptions.ContainerException;
import org.hbrs.se.ws24.domainLayer.UserStory;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class CommandEnter implements Command{

    Container con;
    Scanner sc;
    private Stack<UserStory> addedUserStories;
    public CommandEnter(Container con, Scanner sc){
        this.con = con;
        this.sc=sc;
        this.addedUserStories = new Stack<>();
    }

    @Override
    public void execute(Map<String,String> params) {
        String title = this.readLine("Wie soll Ihre User Story heißen?",sc);
        int ID = this.readLine("Welche ID soll Ihre User Story haben?",sc,0,Integer.MAX_VALUE);
        sc.nextLine();
        String actor = this.readLine("Aus welcher Sicht wird diese UserStory geschrieben?",sc);
        String valueText = this.readLine("Beschreiben Sie den Mehrwert, den die User Story haben soll!",sc);
        String acceptanceCriteria = this.readLine("Welches Akzeptanzkriterium soll Ihre User Story haben?",sc);
        int relValue = this.readLine("Welchen Relativen Mehrwert hat Ihre User Story? (1-5)",sc,1,5);
        int relPenalty = this.readLine("Welche Relative Strafe hat Ihre User Story? (1-5)",sc,1,5);
        int relRisk = this.readLine("Welches Relative Risiko hat Ihre User Story? (1-5)",sc,1,5);
        int expense = this.readLine("Welcher Aufwand hat Ihre User Story?",sc,0,Integer.MAX_VALUE);
        sc.nextLine();
        String project = this.readLine("Zu welchem Projekt gehört Ihre User Story?",sc);

        try{
            UserStory us = new UserStory(ID,title,relValue,relPenalty,expense,relRisk,acceptanceCriteria,project,valueText,actor);
            con.addUserStory(us);
            this.addedUserStories.push(us);
        } catch (ContainerException e) {
            System.out.println("Es ist etwas schief gelaufen! Fehler: "+e.getMessage());
        }
    }

    @Override
    public boolean undo() throws ContainerException {
        UserStory us = this.addedUserStories.pop();
        this.con.deleteUserStory(us.getId());
        return true;
    }

    @Override
    public String getDescription() {
        return "Fügt eine neue User Story hinzu";
    }

    private int readLine(String message, Scanner sc, int min, int max){
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
    private String readLine(String message,Scanner sc){
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
