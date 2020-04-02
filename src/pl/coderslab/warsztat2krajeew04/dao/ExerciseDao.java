package pl.coderslab.warsztat2krajeew04.dao;

import pl.coderslab.warsztat2krajeew04.model.Exercise;
import pl.coderslab.warsztat2krajeew04.utils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDao {
    private static final String CREATE_EXERCISE_QUERY = "INSERT INTO exercises(title, description) VALUES (?, ?);";
    private static final String READ_EXERCISE_QUERY = "SELECT * FROM exercises where id = ?;";
    private static final String UPDATE_EXERCISE_QUERY = "UPDATE exercises SET title = ?, description = ?,  where id = ?;";
    private static final String DELETE_EXERCISE_QUERY = "DELETE FROM exercises WHERE id = ?;";
    private static final String FIND_ALL_EXERCISES_QUERY = "SELECT * FROM exercises;";

    public Exercise create(Exercise exercise) {
        try (Connection connection = DbUtils.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement(CREATE_EXERCISE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                exercise.setId(resultSet.getInt(1));
            }
            return exercise;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Exercise read(int id) {
        try (Connection connection = DbUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(READ_EXERCISE_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                return exercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Exercise exercise) {
        try (Connection connection = DbUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_EXERCISE_QUERY);
            statement.setInt(3, exercise.getId());
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EXERCISE_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Exercise> findAll() {
        List<Exercise> exerciseArrayList = new ArrayList<>();
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_EXERCISES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                exerciseArrayList.add(exercise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exerciseArrayList;
    }
}