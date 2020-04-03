package pl.coderslab.warsztat2krajeew04.controller;

import pl.coderslab.warsztat2krajeew04.dao.ExerciseDao;
import pl.coderslab.warsztat2krajeew04.dao.UsersGroupDao;
import pl.coderslab.warsztat2krajeew04.model.Exercise;
import pl.coderslab.warsztat2krajeew04.model.UsersGroup;

import java.util.Scanner;

public class ExerciseController {
    public static void main(String[] args) {
        System.out.println("Welcome to Exercise administration panel");
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
                addExercise();
            } else if (input.equals("2")) {
                editExercise();
            } else if (input.equals("3")) {
                deleteExercise();
            } else {
                System.out.println("Provide a proper menu option!");
            }
        }
        System.out.println("Program has ended!");
    }

    private static void deleteExercise() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Eneter ExerciseId to delete");
        final int exerciseId = scanner.nextInt();
        try {
            ExerciseDao exerciseDao = new ExerciseDao();
            exerciseDao.delete(exerciseId);
        } catch (Exception e) {
            System.out.println("Invalid delete from database");
        }
    }

    private static void editExercise() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id");
        final int exerciseId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter title");
        final String exerciseTitle = scanner.nextLine();
        System.out.println("Enter description");
        final String exerciseDescription = scanner.nextLine();
        try {
            ExerciseDao exerciseDao = new ExerciseDao();
            Exercise exercise = new Exercise(exerciseId, exerciseTitle, exerciseDescription);
            exerciseDao.update(exercise);
            System.out.println("Exercise edited!");
        } catch (Exception e) {
            System.out.println("Eroor while editing exercise");
        }
    }

    private static void addExercise() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter title");
        final String exerciseTitle = scanner.nextLine();
        System.out.println("Enter description");
        final String exerciseDescription = scanner.nextLine();
        Exercise exercise = new Exercise();
        exercise.setTitle(exerciseTitle);
        exercise.setDescription(exerciseDescription);
        ExerciseDao exerciseDao = new ExerciseDao();
        if (exerciseDao.create(exercise) != null) {
            System.out.println("User saved");
        } else {
            System.out.println("Invalid save to database");
        }
    }
}