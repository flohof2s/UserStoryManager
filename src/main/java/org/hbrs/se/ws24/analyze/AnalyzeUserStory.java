package org.hbrs.se.ws24.analyze;

import org.hbrs.se.ws24.analyze.dto.AnalyzeReturnObject;
import org.hbrs.se.ws24.model.UserStory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.math.*;

public class AnalyzeUserStory implements Analyze<UserStory> {

    @Override
    public AnalyzeReturnObject analyze(UserStory userStory, boolean with_details, boolean with_hints) {
        return new AnalyzeReturnObject(0.0,null,null,"schlecht");
    }

    @Override
    public String getGrade(double score) {
        if(score<0.5){
            return "mangelhaft";
        } else if (score<0.7) {
            return "ausreichend";
        } else if (score<0.8) {
            return "befriedigend";
        } else if (score<0.95) {
            return "gut";
        } else {
            return "sehr gut";
        }
    }

}
