package org.hbrs.se.ws24.analyze;

import org.hbrs.se.ws24.analyze.dto.AnalyzeReturnObject;

import java.util.List;

public interface Analyze<E> {
    public AnalyzeReturnObject analyze(E e, boolean with_details, boolean with_hints);
    public String getGrade(double score);
}
