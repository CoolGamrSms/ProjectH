package com.shaneschulte.projecth.data;

import java.util.ArrayList;
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
    private List<Upgrade> upgradesOffensive;
    private List<Upgrade> upgradesDefensive;
    private List<Upgrade> upgradesHardware;
    private Stats stats;

    public User(
        int id,
        String username,
        String email
    ){
        this(
                id,
                username,
                email,
                false,
                new Level(),
                new ArrayList<Upgrade>(),
                new ArrayList<Upgrade>(),
                new ArrayList<Upgrade>(),
                new Stats()
        );
    }

    public User(
            int id,
            String username,
            String email,
            boolean online,
            Level level,
            List<Upgrade> upgradesOffensive,
            List<Upgrade> upgradesDefensive,
            List<Upgrade> upgradesHardware,
            Stats stats
    ){
        this.id = id;
        this.username = username;
        this.email = email;
        this.online = online;
        this.level = level;
        this.upgradesOffensive = upgradesOffensive;
        this.upgradesDefensive = upgradesDefensive;
        this.upgradesHardware = upgradesHardware;
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

    public List<Upgrade> getUpgradesOffensive(){
        return upgradesOffensive;
    }

    public List<Upgrade> getUpdgradesDefensive(){
        return upgradesDefensive;
    }

    public List<Upgrade> getUpdgradesHardware(){
        return upgradesHardware;
    }

    public Stats getStats(){
        return stats;
    }
}
