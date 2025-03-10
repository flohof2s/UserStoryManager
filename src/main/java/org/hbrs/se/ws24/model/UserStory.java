package org.hbrs.se.ws24.model;
import org.hbrs.se.ws24.analyze.Analyze;
import org.hbrs.se.ws24.analyze.AnalyzeUserStory;
import org.hbrs.se.ws24.analyze.dto.AnalyzeReturnObject;
import org.hbrs.se.ws24.analyze.exceptions.AnalyzeException;
import org.hbrs.se.ws24.persistence.exceptions.PersistenceException;

import java.io.Serializable;
import java.util.Objects;

public class UserStory implements Comparable<UserStory>, Serializable {
    private String titel;
    private String valueText;
    private String actor;
    private int aufwand = 0;
    private int id = 0;
    private int mehrwert = 0;
    private int risk = 0;
    private int strafe = 0;
    private double prio = 0.0;
    private String project;
    private String akzeptanzkriterium;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }




    public UserStory(int id, String titel, int mehrwert, int strafe,
                     int aufwand, int risk,String akzeptanzKriterium,String project,String valueText,String actor) {
        this.id = id;
        this.titel = titel;
        this.mehrwert = mehrwert;
        this.strafe = strafe;
        this.aufwand = aufwand;
        this.risk = risk;
        this.project=project;
        this.akzeptanzkriterium = akzeptanzKriterium;
        this.prio = this.calculatePrio();
        this.valueText=valueText;
        this.actor=actor;
    }

    public UserStory() {
    }



    public String getAkzeptanzkriterium(){
        return this.akzeptanzkriterium;
    }

    public void setAkzeptanzkriterium(String akzeptanzkriterium){
        this.akzeptanzkriterium=akzeptanzkriterium;
    }

    public double getPrio() {
        return prio;
    }

    public void setPrio(double prio) {
        this.prio = prio;
    }

    public String getTitel() {
        return titel;
    }
    public void setTitel(String titel) {
        this.titel = titel;
    }
    public int getAufwand() {
        return aufwand;
    }
    public void setAufwand(int aufwand) {
        this.aufwand = aufwand;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getMehrwert() {
        return mehrwert;
    }
    public void setMehrwert(int mehrwert) {
        this.mehrwert = mehrwert;
    }
    public int getRisk() {
        return risk;
    }
    public void setRisk(int risk) {
        this.risk = risk;
    }
    public int getStrafe() {
        return strafe;
    }
    public void setStrafe(int strafe) {
        this.strafe = strafe;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public float calculatePrio() {
        return (float) (this.getMehrwert()+this.getStrafe())/(this.getAufwand()+this.getRisk());
    }

    /*
     * Methode zum Vergleich zweier UserStories.
     * Vergleich ist implementiert auf Basis des Vergleichs
     * von zwei Prio-Werten.
     */
    public int compareTo(UserStory input) {
        if ( input.getPrio() == this.getPrio() ) {
            return 0;
        }

        if ( input.getPrio() > this.getPrio() ) {
            return 1;
        }
        else return -1;
    }

    @Override
    public String toString() {
        return String.format("%3s %20s %40s %10s %20s %5s %20s",this.getId(),this.getTitel(),this.getValueText(),this.getActor(),this.getAkzeptanzkriterium(),(double)Math.round(this.getPrio()*100)/100,this.getProject());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStory userStory = (UserStory) o;
        return aufwand == userStory.aufwand && id == userStory.id && mehrwert == userStory.mehrwert && risk == userStory.risk && strafe == userStory.strafe && Double.compare(prio, userStory.prio) == 0 && Objects.equals(titel, userStory.titel) && Objects.equals(project, userStory.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titel, aufwand, id, mehrwert, risk, strafe, prio, project);
    }
}
