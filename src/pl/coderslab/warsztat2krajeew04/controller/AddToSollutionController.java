package pl.coderslab.warsztat2krajeew04.controller;

import pl.coderslab.warsztat2krajeew04.dao.SolutionDao;
import pl.coderslab.warsztat2krajeew04.model.Solution;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddToSollutionController {
    public static void main(String[] args) {
        int userId = 0;
        for(String str : args){
            userId=Integer.parseInt(str);
        }
        System.out.println("Enter User Id");
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println();
            System.out.println("Select option and hit enter. Options:");
            System.out.println("1 - Add");
            System.out.println("2 - View");
            System.out.println("0 - Quit");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            } else if (input.equals("1")) {
                add(userId);
            } else if (input.equals("2")) {
                 view(userId);
            } else {
                System.out.println("Provide a proper menu option!");
            }
        }
    }
    public static void view (int userId){
        List<Solution> solutionDaos =  new SolutionDao().findAllByUserId(userId);
        for(Solution solution : solutionDaos){
            System.out.println(" id "+ solution.getId() + " solution name " + solution.getDescription());
        }
    }
    public static void add(int userId){
        List<Solution> solutionDaos =  new SolutionDao().findAllByUserId(userId);
        for(Solution solution : solutionDaos){
            if(solution.getDescription()==null){
                System.out.println(" id "+ solution.getId() + " solution Exercie Id" + solution.getExerciseId());
            }
        }
        System.out.println("Enter Sollution Id");
        Scanner scanner = new Scanner(System.in);
        int sollutionId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Sollution");
        String description =scanner.nextLine();

        Solution solution = new Solution();
        solution.setUpdated(LocalDateTime.now());
        solution.setId(sollutionId);
        solution.setDescription(description);
        new SolutionDao().update(solution);
    }
}
