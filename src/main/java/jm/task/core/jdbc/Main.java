package jm.task.core.jdbc;

//import com.mysql.fabric.jdbc.FabricMySQLDriver;
//String url = "jdbc:mysql://localhost/store?serverTimezone=Europe/Moscow&useSSL=false";

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.HibernateException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Calendar;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
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
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            Driver driver = new FabricMySQLDriver();
//            DriverManager.registerDriver(driver);
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            preparedStatement = connection.prepareStatement(DELETE);
//            preparedStatement.setInt(1,1);
//            preparedStatement.executeUpdate();
//
////            preparedStatement.setInt(1, 1);
////            preparedStatement.setString(2, "Inserted title");
////            preparedStatement.setString(3, "Inserted desc");
////            preparedStatement.setFloat(4, 0.2f);
////            preparedStatement.setBoolean(5, true);
////            preparedStatement.setDate(6, new Date(Calendar.getInstance().getTimeInMillis()));
////            preparedStatement.setBlob(7, new FileInputStream("smile1.jpg"));
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                int id = resultSet.getInt("id");
//                String title = resultSet.getString("title");
//                String desc = resultSet.getString("description");
//                float rating = resultSet.getFloat("rating");
//                boolean published = resultSet.getBoolean("published");
//                Date date = resultSet.getDate("created");
//                byte[] icon = resultSet.getBytes("icon");
//                System.out.println("id: " + id+ ", title: "+title+", desc: " + desc + ", rating: " + rating +
//                        ", published: " + published + ", date: " + date + ", icon length " + icon.length);
//
//            }
//
//        } catch (SQLException e) {
//        }
//    }
//}
//
//
////        Util util = new Util();
////        String str = "select * from users";
////        Statement statement = util.getConnection().createStatement();
////        ResultSet resultSet = statement.executeQuery(str);
////        while (resultSet.next()) {
////            User user = new User();
////            user.setId(resultSet.getLong("id"));
////            user.setName(resultSet.getString("name"));
////            user.setLastName(resultSet.getString("lastname"));
////            user.setAge((byte) resultSet.getInt("age"));
////            System.out.println(user);
//////            long anLong = resultSet.getLong(1);
////            String string = resultSet.getString(2);
////            String string1 = resultSet.getString(3);
////            int anInt1 = resultSet.getInt(4);
////            System.out.println(anLong + " " + string + " " + string1 + " " +anInt1);


//    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "rooot";
//    private static final String INSERT_NEW = "INSERT INTO dish VALUES (?,?,?,?,?,?,?)";
//    private static final String GET_ALL = "SELECT * FROM dish";
//    private static final String DELETE = "DELETE FROM dish WHERE id = ?";