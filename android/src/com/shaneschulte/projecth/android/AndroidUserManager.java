package com.shaneschulte.projecth.android;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shaneschulte.projecth.data.Level;
import com.shaneschulte.projecth.data.Stats;
import com.shaneschulte.projecth.data.Upgrade;
import com.shaneschulte.projecth.data.User;
import com.shaneschulte.projecth.data.UserManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron Barber on 22/11/14.
 */
public class AndroidUserManager implements UserManager {

    //Constants
    //-------------------------------------------------------------------------
    private String typeUser                 = "user";
    private String typeID                   = "id";
    private String typeUsername             = "username";
    private String typeEmail                = "email";
    private String typeOnline               = "online";
    private String typeLevel                = "info"; //TODO: switch this to typeInfo
    private String typeUpgradesOffensive    = "upgradesOffensive";
    private String typeUpgradesDefensive    = "upgradesDefensive";
    private String typeUpgradesHardware     = "upgradesHardware";
    private String typeStats                = "stats";

    private String typeLevel_money          = "money";
    private String typeLevel_data           = "data";
    private String typeLevel_level          = "level";
    private String typeLevel_experience     = "experience";

    private String typeStats_successful     = "hacksSuccessful";
    private String typeStats_count          = "hacksCount";
    //-------------------------------------------------------------------------


    //Interface Implementation
    //-------------------------------------------------------------------------
    @Override
    public void getUser(int id, final Function<User> x) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(typeUser);
        query.whereEqualTo(typeID, id);

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
        ParseObject result = new ParseObject(typeUser);
        updateUser(result, user);
        result.saveInBackground();
    }

    @Override
    public void updateUser(final User user) {
        //TODO: this seems inefficient to have to search for the user a second time

        ParseQuery<ParseObject> query = ParseQuery.getQuery(typeUser);
        query.whereEqualTo(typeID, user.getID());

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
        int id          = obj.getInt(typeID);
        String username = obj.getString(typeUsername);
        String email    = obj.getString(typeEmail);
        boolean online  = obj.getBoolean(typeOnline);
        Level level     = parseLevel(obj.getParseObject(typeLevel));
        List<Upgrade> tO  = parseUpgrades(obj.getParseObject(typeUpgradesOffensive));
        List<Upgrade> tD  = parseUpgrades(obj.getParseObject(typeUpgradesDefensive));
        List<Upgrade> tH  = parseUpgrades(obj.getParseObject(typeUpgradesHardware));
        Stats stats     = parseStats(obj.getParseObject(typeStats));

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
        double money    = obj.getDouble(typeLevel_money);
        double data     = obj.getDouble(typeLevel_data);
        int level       = obj.getInt(typeLevel_level);
        int experience  = obj.getInt(typeLevel_experience);

        return new Level(
                money,
                data,
                level,
                experience
        );
    }

    private Stats parseStats(ParseObject obj){
        int success     = obj.getInt(typeStats_successful);
        int count       = obj.getInt(typeStats_count);

        return new Stats(
                success,
                count
        );
    }

    private List<Upgrade> parseUpgrades(ParseObject obj){
        //TODO: actually parse Upgrades
        return new ArrayList<Upgrade>();
    }
    //-------------------------------------------------------------------------


    //Saving TO PARSE
    //-------------------------------------------------------------------------
    private void updateUser(ParseObject obj, User user){
        obj.put(typeID, user.getID());
        obj.put(typeUsername, user.getUsername());
        obj.put(typeEmail, user.getEmail());
        obj.put(typeOnline, user.isOnline());

        updateLevel(obj.getParseObject(typeLevel), user.getLevel());
        updateUpgrades(obj.getParseObject(typeUpgradesOffensive), user.getUpgradesOffensive());
        updateUpgrades(obj.getParseObject(typeUpgradesDefensive), user.getUpdgradesDefensive());
        updateUpgrades(obj.getParseObject(typeUpgradesHardware), user.getUpdgradesHardware());
        updateStats(obj.getParseObject(typeStats), user.getStats());
    }

    private void updateLevel(ParseObject obj, Level level){
        obj.put(typeLevel_money, level.getMoney());
        obj.put(typeLevel_data, level.getData());
        obj.put(typeLevel_level, level.getLevel());
        obj.put(typeLevel_experience, level.getExperience());
    }

    private void updateStats(ParseObject obj, Stats stats){
        obj.put(typeStats_successful, stats.getHacksSuccessful());
        obj.put(typeStats_count, stats.getHacksCount());
    }

    private void updateUpgrades(ParseObject obj, List<Upgrade> upgrades){
        //TODO: actually update these
    }
    //-------------------------------------------------------------------------
}
