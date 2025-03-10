package org.hbrs.se.ws24.domainLayer;

public class Actor {
    private String name;
    public Actor(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Actor actor = (Actor) obj;
        return this.getName().equals(actor.getName());
    }
}
