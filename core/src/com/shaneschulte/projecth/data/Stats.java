package com.shaneschulte.projecth.data;

/**
 * Created by Aaron Barber on 22/11/14.
 */
public class Stats {

    private int hacksSuccessful;
    private int hacksCount;

    public Stats(
            int hacksSuccessful,
            int hacksCount
    ){
        this.hacksSuccessful = hacksSuccessful;
        this.hacksCount = hacksCount;
    }

    public int getHacksSuccessful(){
        return hacksSuccessful;
    }

    public int getHacksCount(){
        return hacksCount;
    }
}
