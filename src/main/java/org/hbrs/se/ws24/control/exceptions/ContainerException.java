package org.hbrs.se.ws24.control.exceptions;

public class ContainerException extends Exception{
    public ContainerException(int id){
        super("Das Member-Object mit der ID "+id+" ist bereits vorhanden!");
    }

    public ContainerException(String message){
        super(message);
    }
}