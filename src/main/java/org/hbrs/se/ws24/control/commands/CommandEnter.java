package org.hbrs.se.ws24.control.commands;

import org.hbrs.se.ws24.control.Container;
import org.hbrs.se.ws24.control.exceptions.ContainerException;
import org.hbrs.se.ws24.model.UserStory;

import java.util.Map;
import java.util.Scanner;

public class CommandEnter implements Command{

    Container con;
    Scanner sc;
    public CommandEnter(Container con, Scanner sc){
        this.con = con;
        this.sc=sc;
    }

    @Override
    public void execute(Map<String,String> params) {
        String title = this.readLine("Wie soll Ihre User Story heißen?",sc);
        int ID = this.readLine("Welche ID soll Ihre User Story haben?",sc,0,Integer.MAX_VALUE);
        sc.nextLine();
        String acceptanceCriteria = this.readLine("Welches Akzeptanzkriterium soll Ihre User Story haben?",sc);
        int relValue = this.readLine("Welchen Relativen Mehrwert hat Ihre User Story? (1-5)",sc,1,5);
        int relPenalty = this.readLine("Welche Relative Strafe hat Ihre User Story? (1-5)",sc,1,5);
        int relRisk = this.readLine("Welches Relative Risiko hat Ihre User Story? (1-5)",sc,1,5);
        int expense = this.readLine("Welcher Aufwand hat Ihre User Story?",sc,0,Integer.MAX_VALUE);
        sc.nextLine();
        String project = this.readLine("Zu welchem Projekt gehört Ihre User Story?",sc);

        try{
            con.addUserStory(new UserStory(ID,title,relValue,relPenalty,expense,relRisk,acceptanceCriteria,project));
        } catch (ContainerException e) {
            System.out.println("Es ist etwas schief gelaufen! Fehler: "+e.getMessage());
        }
    }

    @Override
    public void undo() {

    }

    @Override
    public String getDescription() {
        return "Fügt eine neue User Story hinzu";
    }
}
