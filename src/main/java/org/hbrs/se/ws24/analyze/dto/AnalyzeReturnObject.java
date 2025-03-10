package org.hbrs.se.ws24.analyze.dto;

import java.util.ArrayList;

public class AnalyzeReturnObject {
    private final ArrayList<String> details,hints;
    private final double score;
    private final String grade;

    public AnalyzeReturnObject(double score,ArrayList<String> details,ArrayList<String> hints,String grade){
        this.score = score;
        this.details=details;
        this.hints=hints;
        this.grade=grade;
    }

    public String getGrade() {
        return grade;
    }
    public ArrayList<String> getDetails() {
        return details;
    }

    public ArrayList<String> getHints() {
        return hints;
    }

    public double getScore() {
        return score;
    }
}
