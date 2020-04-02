package pl.coderslab.warsztat2krajeew04.dao;

import pl.coderslab.warsztat2krajeew04.model.User;
import pl.coderslab.warsztat2krajeew04.model.UsersGroup;
import pl.coderslab.warsztat2krajeew04.utils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersGroupDao {
    private static final String CREATE_USERS_GROUP_QUERY = "INSERT INTO users_group(id, name) VALUES (?,?);";
    private static final String READ_USERS_GROUP_QUERY = "SELECT * FROM users_group where id = ?;";
    private static final String UPDATE_USERS_GROUP_QUERY = "UPDATE users_group SET id = ?, name = ?,  where id = ?;";
    private static final String DELETE_USERS_GROUP_QUERY = "DELETE FROM users_group WHERE id = ?;";
    private static final String FIND_ALL_USERS_GROUP_QUERY = "SELECT * FROM users_group;";

    public UsersGroup create(UsersGroup usersGroup) {
        try (Connection connection = DbUtils.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement(CREATE_USERS_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, usersGroup.getId());
            statement.setString(2, usersGroup.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                usersGroup.setId(resultSet.getInt(1));
            }
            return usersGroup;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UsersGroup read(int id) {
        try (Connection connection = DbUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(READ_USERS_GROUP_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UsersGroup user = new UsersGroup();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(UsersGroup users) {
        try (Connection connection = DbUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_GROUP_QUERY);
            statement.setString(2, users.getName());
            statement.setInt(1, users.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_GROUP_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UsersGroup> findAll() {
        List<UsersGroup> usersGroups = new ArrayList<>();
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS_GROUP_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                UsersGroup userToAdd = new UsersGroup();
                userToAdd.setId(resultSet.getInt("id"));
                userToAdd.setName(resultSet.getString("name"));
                usersGroups.add(userToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersGroups;
    }
}