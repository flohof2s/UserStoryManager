package org.hbrs.se.ws24.view;

import org.hbrs.se.ws24.control.Container;

import java.util.Scanner;

public class Client {
    private Container con;
    private Scanner sc;
    public Client(Container con){
        this.con=con;
        this.sc = new Scanner(System.in);
        //this.start(this.con,this.sc);
        sc.close();
    }
}
