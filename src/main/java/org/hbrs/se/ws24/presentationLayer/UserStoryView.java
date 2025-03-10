package org.hbrs.se.ws24.presentationLayer;

import org.hbrs.se.ws24.domainLayer.UserStory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserStoryView {
    public static void dump(List<UserStory> liste, String sortAfterProject){
        List<UserStory> outputList = liste;
        if(!sortAfterProject.isEmpty()){
            outputList = liste
                    .stream()
                    .filter((userStory -> userStory.getProject().equals(sortAfterProject)))
                    .collect(Collectors.toList());
        }

        Collections.sort(outputList);
        System.out.println(String.format("%3s %20s %40s %10s %20s %5s %20s","ID","Titel","Mehrwert","Akteur","Akzeptanzkriterium","Prio","Projekt"));
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        for(UserStory item : outputList){
            System.out.println(item);
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
