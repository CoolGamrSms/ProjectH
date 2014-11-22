package com.shaneschulte.projecth.data;

import java.util.List;

/**
 * Created by Aaron Barber on 22/11/14.
 */
public class User {

    private int id;
    private String username;
    private String email;
    private boolean online;
    private Level level;
    private List<Trait> traitsOffensive;
    private List<Trait> traitsDefensive;
    private List<Trait> traitsHardware;
    private Stats stats;

    public User(
            int id,
            String username,
            String email,
            boolean online,
            Level level,
            List<Trait> traitsOffensive,
            List<Trait> traitsDefensive,
            List<Trait> traitsHardware,
            Stats stats
    ){
        this.id = id;
        this.username = username;
        this.email = email;
        this.online = online;
        this.level = level;
        this.traitsOffensive = traitsOffensive;
        this.traitsDefensive = traitsDefensive;
        this.traitsHardware = traitsHardware;
        this.stats = stats;
    }

    public int getID(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public boolean isOnline(){
        return online;
    }

    public Level getLevel(){
        return level;
    }

    public List<Trait> getTraitsOffensive(){
        return traitsOffensive;
    }

    public List<Trait> getTraitsDefensive(){
        return traitsDefensive;
    }

    public List<Trait> getTraitsHardware(){
        return traitsHardware;
    }

    public Stats getStats(){
        return stats;
    }
}
