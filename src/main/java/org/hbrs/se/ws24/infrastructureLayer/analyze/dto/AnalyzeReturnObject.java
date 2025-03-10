package org.hbrs.se.ws24.infrastructureLayer.analyze.dto;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeReturnObject {
    private final List<String> details,hints;
    private final double score;
    private final String grade;

    public AnalyzeReturnObject(double score,List<String> details,List<String> hints,String grade){
        this.score = score;
        this.details=details;
        this.hints=hints;
        this.grade=grade;
    }

    public String getGrade() {
        return grade;
    }
    public List<String> getDetails() {
        return details;
    }

    public List<String> getHints() {
        return hints;
    }

    public double getScore() {
        return score;
    }
}
