package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Driver;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

//        userService.saveUser("John", "Smith", (byte) 23);
//        System.out.println("User: " + userService.getAllUsers().get(0).getName() + " добавлен в базу данных");
//        userService.saveUser("Jane", "Smith", (byte) 19);
//        System.out.println("User: " + userService.getAllUsers().get(1).getName() + " добавлен в базу данных");
//        userService.saveUser("Jack", "Smith", (byte) 26);
//        System.out.println("User: " + userService.getAllUsers().get(2).getName() + " добавлен в базу данных");
//        userService.saveUser("Jill", "Smith", (byte) 15);
//        System.out.println("User: " + userService.getAllUsers().get(3).getName() + " добавлен в базу данных");

        //userService.removeUserById(3);

        //System.out.println(userService.getAllUsers().toString());
        //userService.cleanUsersTable();
        //userService.dropUsersTable();
    }
}
