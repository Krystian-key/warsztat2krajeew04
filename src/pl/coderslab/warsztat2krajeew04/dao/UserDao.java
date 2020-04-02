package pl.coderslab.warsztat2krajeew04.dao;

import pl.coderslab.warsztat2krajeew04.model.User;
import pl.coderslab.warsztat2krajeew04.utils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password, user_group_id) VALUES (?, ?, ?,?);";
    private static final String READ_USER_QUERY = "SELECT * FROM users where id = ?;";
    private static final String FIND_ALL_USERS_BY_GROUP_ID_QUERY = "SELECT * FROM users where user_group_id = ?;";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, email = ?, password = ?,user_group_id = ?,  where id = ?;";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?;";
    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users;";

    public User create(User user) {
        try (Connection connection = DbUtils.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getUserGroupId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int id) {
        try (Connection connection = DbUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPasswordString(resultSet.getString("password"));
                user.setUserGroupId(resultSet.getInt("user_group_id"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) {
        try (Connection connection = DbUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY);
            statement.setInt(5, user.getId());
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getUserGroupId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User userToAdd = new User();
                userToAdd.setId(resultSet.getInt("id"));
                userToAdd.setUsername(resultSet.getString("username"));
                userToAdd.setEmail(resultSet.getString("email"));
                userToAdd.setUserGroupId(resultSet.getInt("user_group_id"));
                userToAdd.setPasswordString(resultSet.getString("password"));
                userList.add(userToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List<User> findAllByGroupId(int groupId) {
        List<User> userArrayList = new ArrayList<>();
        try (Connection connection = DbUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS_BY_GROUP_ID_QUERY);
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPasswordString(resultSet.getString("password"));
                user.setUserGroupId(resultSet.getInt("user_group_id"));
                userArrayList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userArrayList;
    }
}