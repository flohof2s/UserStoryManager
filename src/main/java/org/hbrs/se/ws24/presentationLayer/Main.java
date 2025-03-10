package org.hbrs.se.ws24.presentationLayer;

import org.hbrs.se.ws24.infrastructureLayer.analyze.AnalyzeUserStory;
import org.hbrs.se.ws24.applicationLayer.Container;
import org.hbrs.se.ws24.infrastructureLayer.persistence.PersistenceStrategyMongoDB;

public class Main {
    public static void main(String[] args){
        Container con = Container.getInstance();
        con.setPersistenceStrategy(new PersistenceStrategyMongoDB());
        con.setAnalyzeStrategy(new AnalyzeUserStory());
        new Client(con);
    }
}
