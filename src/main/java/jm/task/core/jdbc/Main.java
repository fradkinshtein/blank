package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("ivan", "zar", (byte) 10);
        userService.removeUserById(5);
        userService.saveUser("igor", "zh", (byte) 11);
        userService.saveUser("3", "2", (byte) 12);
        userService.saveUser("4", "2", (byte) 13);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
