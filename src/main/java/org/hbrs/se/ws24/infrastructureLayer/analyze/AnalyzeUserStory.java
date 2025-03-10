package org.hbrs.se.ws24.infrastructureLayer.analyze;

import org.hbrs.se.ws24.infrastructureLayer.analyze.dto.AnalyzeReturnObject;
import org.hbrs.se.ws24.applicationLayer.Container;
import org.hbrs.se.ws24.domainLayer.UserStory;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeUserStory implements Analyze<UserStory> {

    @Override
    public AnalyzeReturnObject analyze(UserStory userStory, Container con, boolean with_details, boolean with_hints) {
        double score = 100.0;
        List<String> details = new ArrayList<>();
        List<String> hints = new ArrayList<>();

        if(userStory.getValueText().equals("")){
            score -= 30.0;
            details.add("Kein schriftlicher Mehrwert zu erkennen (-30%)");
            hints.add("Fügen sie einen schriftlichen Mehrwert hinzu!");
        }

        if(!con.containsActor(userStory.getActor())){
            score -= 20.0;
            details.add("Akteur (\""+userStory.getActor()+"\") ist nicht bekannt (-20%)");
            hints.add("Registrieren Sie einen neuen Akteur!");
        }

        if(userStory.getTitel().contains("und")){
            score -= 20.0;
            details.add("Verwendung von \"und\" erkannt! (-20%)");
            hints.add("Brechen Sie diese User Story auf!");
        }

        if(userStory.getAkzeptanzkriterium().equals("")){
            score -= 10.0;
            details.add("Kein Akzeptanzkriterium zu erkennen! (-10%)");
            hints.add("Fügen Sie ein Akzeptanzkriterium hinzu!");
        }



        return new AnalyzeReturnObject(score,details,hints,this.getGrade(score));
    }

    @Override
    public String getGrade(double score) {
        if(score<50.0){
            return "mangelhaft";
        } else if (score<70.0) {
            return "ausreichend";
        } else if (score<80.0) {
            return "befriedigend";
        } else if (score<95.0) {
            return "gut";
        } else {
            return "sehr gut";
        }
    }

}
