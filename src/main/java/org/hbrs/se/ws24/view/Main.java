package org.hbrs.se.ws24.view;

import org.hbrs.se.ws24.control.Container;
import org.hbrs.se.ws24.persistence.PersistenceStrategyStream;

public class Main {
    public static void main(String[] args){
        Container con = Container.getInstance();
        con.setPersistenceStrategy(new PersistenceStrategyStream());
        new Client(con);
    }
}
