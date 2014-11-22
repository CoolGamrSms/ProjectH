package com.shaneschulte.projecth.data;

/**
 * Created by Aaron Barber on 22/11/14.
 */
public class Upgrade {

    private String name;
    private boolean enabled;

    public Upgrade(
            String name,
            boolean enabled
    ){
        this.name = name;
        this.enabled = enabled;
    }

    public String getName(){
        return name;
    }

    public boolean isEnabled(){
        return enabled;
    }
}
