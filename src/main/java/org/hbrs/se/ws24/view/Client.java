package org.hbrs.se.ws24.view;

import org.hbrs.se.ws24.analyze.exceptions.AnalyzeException;
import org.hbrs.se.ws24.control.Container;
import org.hbrs.se.ws24.control.commands.*;

import java.util.*;

public class Client {
    private Container con;
    private Scanner sc;

    private Map<String,Command> commands;
    private Stack<Command> commandStack;
    public Client(Container con){
        this.con=con;
        this.sc = new Scanner(System.in);
        this.commands = this.initCommands();
        this.commandStack = new Stack<>();

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
            }else if(command[0].equals("undo")) {
                boolean undo = false;
                while(!undo){
                    if(this.commandStack.isEmpty()){
                        System.out.println("nothing to undo!");
                        break;
                    }
                    Command comm = this.commandStack.pop();
                    try{
                        undo = comm.undo();
                    }
                    catch(Exception e){
                        System.out.println("Es ist ein Fehler aufgetreten: "+e.getMessage());
                    }
                }
            }else{
                try {
                    Command comm = this.commands.get(command[0]);
                    comm.execute(this.getParams(command));
                    this.commandStack.push(comm);

                } catch (Exception e) {
                    System.out.println("Es ist ein Fehler aufgetreten: "+e.getMessage());
                }
            }
        }
    }

    private Map<String,String> getParams(String[] paramList){
        Map<String,String> params = new HashMap<>();
        for(int i=1;i<paramList.length;i+=2){

            //Sonderfall: Erster Parameter kann ohne Argument angegeben werden. Dann ist es eine ID
            if(i==1 && !paramList[i].startsWith("-")){
                params.put("ID",paramList[i]);
                i--;
                continue;
            }
            if(paramList.length==i+1){
                params.put(paramList[i].replace("-",""),"");
            }else{
                if(paramList[i+1].startsWith("--")){
                    params.put(paramList[i].replace("-",""),"");
                    i--;
                }
                else{
                    params.put(paramList[i].replace("-",""),paramList[i+1]);
                }
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
        map.put("analyze",new CommandAnalyze(this.con));
        map.put("addElement",new CommandAddElement(this.con));
        map.put("actors",new CommandActors(this.con));

        Map<String,String> commandDescriptions = new HashMap<>();
        map.forEach( (k, v) -> { commandDescriptions.put(k,v.getDescription()); } );

        map.put("help",new CommandHelp(commandDescriptions));
        map.put("undo",new CommandUndo());
        return map;
    }
}
