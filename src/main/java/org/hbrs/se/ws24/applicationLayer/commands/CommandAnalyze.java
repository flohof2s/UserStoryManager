package org.hbrs.se.ws24.applicationLayer.commands;

import org.hbrs.se.ws24.infrastructureLayer.analyze.dto.AnalyzeReturnObject;
import org.hbrs.se.ws24.infrastructureLayer.analyze.exceptions.AnalyzeException;
import org.hbrs.se.ws24.applicationLayer.Container;

import java.util.Map;

public class CommandAnalyze implements Command {
    private Container con;
    public CommandAnalyze(Container con){
        this.con = con;
    }

    @Override
    public void execute(Map<String, String> params) throws IllegalArgumentException, AnalyzeException {
        if(!params.containsKey("ID") && !params.containsKey("all")){
            throw new IllegalArgumentException("Es muss entweder eine ID oder der Parameter `--all` angegeben werden!");
        }

        if(params.containsKey("ID")&&params.containsKey("all")){
            throw new IllegalArgumentException("Es muss entweder eine ID oder der Parameter `--all` angegeben werden!");
        }
        else if(params.containsKey("ID")) {
            int id;
            try {
                id = Integer.parseInt(params.get("ID"));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Angebene ID muss eine gültige Zahl sein!");
            }

            if (!con.containsID(id)){
                throw new IllegalArgumentException("Angebene ID kann nicht gefunden werden");
            }

            AnalyzeReturnObject aro = con.analyze(con.getUserStoryByID(id),params.containsKey("details"),params.containsKey("hints"));

            String returnString = "Die User Story mit der ID "+id+" hat folgende Qualität:\n"+aro.getScore()+"% ("+aro.getGrade()+")";
            if(params.containsKey("details")){
                returnString += "\n\nDetails:\n";
                if(aro.getDetails().size()==0){
                    returnString+="Alles ok\n";
                }
                for(String detail : aro.getDetails()){
                    returnString+=detail+"\n";
                }
            }
            if(params.containsKey("hints")){
                returnString += "\n\nHints:\n";
                if(aro.getHints().size()==0){
                    returnString+="Alles ok\n";
                }
                for(String hint : aro.getHints()){
                    returnString+=hint+"\n";
                }
            }
            System.out.println(returnString);
        }
        else if (params.containsKey("all")) {
            if(params.containsKey("details") || params.containsKey("hints")){
                throw new IllegalArgumentException("Details und/oder hints können nur bei der Analyse einer einzelnen User Story ausgegeben werden!");
            }

            System.out.println("Ihre "+con.size()+" User Stories haben durchschnittlich folgende Qualität: "+con.analyze()+"% ("+con.getGrade(con.analyze())+")");
        }

    }

    @Override
    public boolean undo() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Analysieren Sie eine oder mehrere User Stories! --all: Durchscnittswert aller User Stories | {ID} --details: Genauere Aufschlüsselung des Werts | {ID} --hints: Verbesserungsvorschläge";
    }
}
