package org.hbrs.se.ws24.view;

import org.hbrs.se.ws24.control.Container;
import org.hbrs.se.ws24.control.commands.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client {
    private Container con;
    private Scanner sc;

    private Map<String,Command> commands;
    public Client(Container con){
        this.con=con;
        this.sc = new Scanner(System.in);
        this.commands = this.initCommands();

        this.start(this.con,this.sc);
        sc.close();
    }

    private void start(Container con, Scanner sc) {
        String[] command = new String[]{""};
        while (!command[0].equals("exit")) {
            System.out.println("Geben Sie Ihr Befehl ein:");
            System.out.print("> ");
            command = sc.nextLine().split(" ");
            if(command[0].equals("exit")){
                continue;
            }
            if(!this.commands.containsKey(command[0])){
                System.out.println("Der Befehl \"" + command[0] + "\" wurde nicht erkannt. Nutze \"help\" für Hilfe!");
            }else{
                this.commands.get(command[0]).execute(this.getParams(command));
            }
        }
    }

    private Map<String,String> getParams(String[] paramList){
        Map<String,String> params = new HashMap<>();
        for(int i=1;i<paramList.length;i+=2){
            if(paramList.length==i+1){
                params.put(paramList[i].replace("-",""),"");
            }else{
                params.put(paramList[i].replace("-",""),paramList[i+1]);
            }

        }
        return params;
    }

    private Map<String,Command> initCommands(){
        Map<String,Command> map = new HashMap<>();
        map.put("enter",new CommandEnter(this.con,this.sc));
        map.put("store",new CommandStore(this.con));
        map.put("dump",new CommandDump(this.con));
        map.put("load",new CommandLoad(this.con));

        Map<String,String> commandDescriptions = new HashMap<>();
        map.forEach( (k, v) -> { commandDescriptions.put(k,v.getDescription()); } );

        map.put("help",new CommandHelp(commandDescriptions));
        return map;
    }
}
