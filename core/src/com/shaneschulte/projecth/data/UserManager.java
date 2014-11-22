package com.shaneschulte.projecth.data;

/**
 * Created by Aaron Barber on 22/11/14.
 */
public interface UserManager {

    public abstract class Function<T>{
        public abstract void execute(T param);
    }

    public void getUser(int id, final Function<User> callback);

    public void createUser(User user);

    public void updateUser(User user);
}
