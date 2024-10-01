package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final UserDaoJDBCImpl INSTANCE = new UserDaoJDBCImpl();

    public UserDaoJDBCImpl() {
    }
    public static UserDaoJDBCImpl getInstance() {
        return INSTANCE;
    }

    public void createUsersTable() {
        String CREATE = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "lastName VARCHAR(255) NOT NULL," +
                "age SMALLINT)";
        try (Connection con = Util.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(CREATE)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String DROP = "DROP TABLE IF EXISTS users";
        try (Connection con = Util.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(DROP)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SAVE_USER = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection con = Util.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SAVE_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            int id = preparedStatement.executeUpdate();
            if (id > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String DELETE_SQL = "DELETE FROM users WHERE id = ?";
        try (Connection con = Util.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String GET_ALL_SQL = "SELECT * FROM users";
        try (Connection con = Util.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(GET_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        String CLEAN = "DELETE FROM users";
        try (Connection con = Util.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(CLEAN)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
