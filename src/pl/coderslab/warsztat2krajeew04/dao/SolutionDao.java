package pl.coderslab.warsztat2krajeew04.dao;

import pl.coderslab.warsztat2krajeew04.model.Exercise;
import pl.coderslab.warsztat2krajeew04.model.Solution;
import pl.coderslab.warsztat2krajeew04.utils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao {
    private static final String CREATE_SOLUTION_QUERY = "INSERT INTO solution(id, created, updated, description, exercise_id, users_id) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String READ_SOLUTION_QUERY = "SELECT * FROM solution where id = ?;";
    private static final String UPDATE_SOLUTION_QUERY = "UPDATE solution SET id = ?, created = ?, updated= ?, description = ?, exercise_id = ?, users_id = ?,  where id = ?;";
    private static final String DELETE_SOLUTION_QUERY = "DELETE FROM solution WHERE id = ?;";
    private static final String FIND_ALL_EXERCISE_QUERY = "SELECT * FROM exercise;";

    public Solution create(Solution solution) {
        try (Connection connection = DbUtils.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, solution.getId());
            statement.setString(2, solution.getCreated().toString());
            statement.setString(3, solution.getUpdated().toString());
            statement.setString(4, solution.getDescription());
            statement.setInt(5, solution.getExerciseId());
            statement.setInt(6, solution.getUsersId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution read(int id) {
        try (Connection connection = DbUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                solution.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                solution.setDescription(resultSet.getString("description"));
                solution.setExerciseId(resultSet.getInt("exercise_id"));
                solution.setUsersId(resultSet.getInt("users_id"));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Solution solution) {
        try (Connection connection = DbUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setInt(1, solution.getId());
            statement.setString(2, solution.getCreated().toString());
            statement.setString(3, solution.getUpdated().toString());
            statement.setString(4, solution.getDescription());
            statement.setInt(5, solution.getExerciseId());
            statement.setInt(6, solution.getUsersId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SOLUTION_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Solution> findAll() {
        List<Solution> solutionArrayList = new ArrayList<>();
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_EXERCISE_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                solution.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                solution.setDescription(resultSet.getString("description"));
                solution.setId(resultSet.getInt("exercise_id"));
                solution.setId(resultSet.getInt("users_id"));
                solutionArrayList.add(solution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return solutionArrayList;
    }
}