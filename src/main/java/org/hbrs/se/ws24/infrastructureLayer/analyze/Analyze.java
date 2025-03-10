package org.hbrs.se.ws24.infrastructureLayer.analyze;

import org.hbrs.se.ws24.infrastructureLayer.analyze.dto.AnalyzeReturnObject;
import org.hbrs.se.ws24.applicationLayer.Container;

public interface Analyze<E> {
    public AnalyzeReturnObject analyze(E e, Container con, boolean with_details, boolean with_hints);
    public String getGrade(double score);
}
