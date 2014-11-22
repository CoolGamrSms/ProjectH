package com.shaneschulte.projecth.android;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shaneschulte.projecth.data.Level;
import com.shaneschulte.projecth.data.Stats;
import com.shaneschulte.projecth.data.Trait;
import com.shaneschulte.projecth.data.User;
import com.shaneschulte.projecth.data.UserFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron Barber on 22/11/14.
 */
public class AndroidUserFactory implements UserFactory{

    //Constants
    //-------------------------------------------------------------------------
    private String userType = "user";
    //-------------------------------------------------------------------------


    //Interface Implementation
    //-------------------------------------------------------------------------
    @Override
    public void getUser(int id, final Function<User> x) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(userType);
        query.whereEqualTo("id", id);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null && parseObjects.size() == 1) {
                    x.execute(parseUser(parseObjects.get(0)));
                } else {
                    //TODO: probably should have different error checks for no results and parse errors
                    x.execute(null);
                }
            }
        });
    }

    @Override
    public void createUser(User user){
        ParseObject result = new ParseObject(userType);
        updateUser(result, user);
        result.saveInBackground();
    }

    @Override
    public void updateUser(final User user) {
        //TODO: this seems inefficient to have to search for the user a second time

        ParseQuery<ParseObject> query = ParseQuery.getQuery(userType);
        query.whereEqualTo("id", user.getID());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null && parseObjects.size() == 1) {
                    ParseObject result = parseObjects.get(0);
                    updateUser(result, user);
                    result.saveInBackground();
                } else {
                    //TODO: probably should error check
                }
            }
        });

    }
    //-------------------------------------------------------------------------


    //Reading FROM PARSE
    //-------------------------------------------------------------------------
    private User parseUser(ParseObject obj){
        int id          = obj.getInt("id");
        String username = obj.getString("username");
        String email    = obj.getString("email");
        boolean online  = obj.getBoolean("online");
        Level level     = parseLevel(obj.getParseObject("level"));
        List<Trait> tO  = parseTraits(obj.getParseObject("traitsOffensive"));
        List<Trait> tD  = parseTraits(obj.getParseObject("traitsDefensive"));
        List<Trait> tH  = parseTraits(obj.getParseObject("traitsHardware"));
        Stats stats     = parseStats(obj.getParseObject("stats"));

        return new User(
                id,
                username,
                email,
                online,
                level,
                tO,
                tD,
                tH,
                stats
        );
    }

    private Level parseLevel(ParseObject obj){
        double money    = obj.getDouble("money");
        double data     = obj.getDouble("data");
        int level       = obj.getInt("level");
        int experience  = obj.getInt("experience");

        return new Level(
                money,
                data,
                level,
                experience
        );
    }

    private Stats parseStats(ParseObject obj){
        int success     = obj.getInt("hacksSuccessful");
        int count       = obj.getInt("hacksCount");

        return new Stats(
                success,
                count
        );
    }

    private List<Trait> parseTraits(ParseObject obj){
        //TODO: actually parse traits
        return new ArrayList<Trait>();
    }
    //-------------------------------------------------------------------------


    //Saving TO PARSE
    //-------------------------------------------------------------------------
    private void updateUser(ParseObject obj, User user){
        obj.put("id", user.getID());
        obj.put("username", user.getUsername());
        obj.put("email", user.getEmail());
        obj.put("online", user.isOnline());

        updateLevel(obj.getParseObject("level"), user.getLevel());
        updateTraits(obj.getParseObject("traitsOffensive"), user.getTraitsOffensive());
        updateTraits(obj.getParseObject("traitsDefensive"), user.getTraitsDefensive());
        updateTraits(obj.getParseObject("traitsHardware"), user.getTraitsHardware());
        updateStats(obj.getParseObject("stats"), user.getStats());
    }

    private void updateLevel(ParseObject obj, Level level){
        obj.put("money", level.getMoney());
        obj.put("data", level.getData());
        obj.put("level", level.getLevel());
        obj.put("experience", level.getExperience());
    }

    private void updateStats(ParseObject obj, Stats stats){
        obj.put("hacksSuccessful", stats.getHacksSuccessful());
        obj.put("hacksCount", stats.getHacksCount());
    }

    private void updateTraits(ParseObject obj, List<Trait> traits){
        //TODO: actually update these
    }
    //-------------------------------------------------------------------------
}
