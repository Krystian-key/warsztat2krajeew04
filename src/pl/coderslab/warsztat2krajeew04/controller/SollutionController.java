package pl.coderslab.warsztat2krajeew04.controller;

import pl.coderslab.warsztat2krajeew04.dao.ExerciseDao;
import pl.coderslab.warsztat2krajeew04.dao.SolutionDao;
import pl.coderslab.warsztat2krajeew04.dao.UserDao;
import pl.coderslab.warsztat2krajeew04.dao.UsersGroupDao;
import pl.coderslab.warsztat2krajeew04.model.Exercise;
import pl.coderslab.warsztat2krajeew04.model.Solution;
import pl.coderslab.warsztat2krajeew04.model.User;
import pl.coderslab.warsztat2krajeew04.model.UsersGroup;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SollutionController {
    public static void main(String[] args) {
        System.out.println("Welcome to Sollution administration panel");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Select option and hit enter. Options:");
            System.out.println("1 - Add");
            System.out.println("2 - Edit");
            System.out.println("3 - Delete");
            System.out.println("0 - Quit");
            String input = scanner.nextLine();

            if (input.equals("0")) {
                break;
            } else if (input.equals("1")) {
                addSollution();
            } else if (input.equals("2")) {
                viewSollution();
            } else {
                System.out.println("Provide a proper menu option!");
            }
        }
        System.out.println("Program has ended!");
    }

    private static void addSollution() {
        List<User> userList = new UserDao().findAll();
        for (User user : userList) {
            System.out.println("id " + user.getId() + " name " + user.getUsername());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter User Id");
        final int userId = Integer.parseInt(scanner.nextLine());

        List<Exercise> exercises = new ExerciseDao().findAll();
        for (Exercise exercise : exercises) {
            System.out.println("id " + exercise.getId() + " Sollution " + exercise.getDescription());
        }
        System.out.println("type Exercise Id");
        final int exerciseId = Integer.parseInt(scanner.nextLine());
        Solution solution = new Solution();
        solution.setExerciseId(exerciseId);
        solution.setUsersId(userId);
        solution.setCreated(LocalDateTime.now());
        new SolutionDao().create(solution);
    }

    private static void viewSollution() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Eneter User Id, to see Sollution");
        final int userId = Integer.parseInt(scanner.nextLine());
        List<Solution> solutionList = new SolutionDao().findAllByUserId(userId);
        for (Solution solution : solutionList) {
            System.out.println("id " + solution.getId() + " name: " + solutionList);
        }
    }
}
