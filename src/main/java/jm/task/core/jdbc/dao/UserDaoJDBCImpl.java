package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        String sqlCommand = "CREATE TABLE users (id BIGINT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(25), lastName VARCHAR(40), " +
                "age SMALLINT NOT NULL, PRIMARY KEY (id))";

        try (Connection connection = Util.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Mistake during table creation");
        }
    }

    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE users";
        try (Connection connection = Util.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.getMySQLConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {

            prepareStatement.setString(1, name);
            prepareStatement.setString(2, lastName);
            prepareStatement.setByte(3, age);
            prepareStatement.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();
        } catch (SQLException e) {

        }
    }

    public void removeUserById(long id) {
         try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement()) {

            statement.execute("DELETE FROM users WHERE id");
        } catch (SQLException e) {
             System.out.println("Mistake removing user by Id");
        }
    }

    public List<User> getAllUsers() {
        List<User> arr = new ArrayList<>();
        String sqlCommand = "SELECT * FROM users";
        try (Connection connection = Util.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
             ResultSet resultSet = preparedStatement.executeQuery(sqlCommand)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                arr.add(user);
                connection.commit();
            }
        } catch (SQLException e) {

        }
        return arr;
    }

    public void cleanUsersTable() {
        String sqlCommand = "DELETE FROM users where id is not null ";

        try (Connection connection = Util.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.executeUpdate(sqlCommand);
        } catch (SQLException e) {

        }
    }
}