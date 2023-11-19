package org.iliasen;


import org.iliasen.models.Auto;
import org.iliasen.models.User;
import org.iliasen.repositories.UserRepository;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserRepository userService = new UserRepository();
        User user = new User("Masha",26);
        userService.saveUser(user);
        Auto ferrari = new Auto("Ferrari", "red");
        ferrari.setUser(user);
        user.addAuto(ferrari);
        Auto ford = new Auto("Ford", "black");
        ford.setUser(user);
        user.addAuto(ford);
        userService.updateUser(user);
    }

}