package com.shaneschulte.projecth.data;

/**
 * Created by Aaron Barber on 22/11/14.
 */
public class Level {

    private double money;
    private double data;
    private int level;
    private int experience;

    public Level(){
        this(0, 0, 0, 0);
    }

    public Level(
            double money,
            double data,
            int level,
            int experience
    ){
        this.money = money;
        this.data = data;
        this.level = level;
        this.experience = experience;
    }

    public double getMoney(){
        return money;
    }

    public double getData(){
        return data;
    }

    public int getLevel(){
        return level;
    }

    public int getExperience(){
        return experience;
    }
}
