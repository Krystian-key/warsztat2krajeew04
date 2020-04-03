package pl.coderslab.warsztat2krajeew04.controller;

import pl.coderslab.warsztat2krajeew04.dao.UserDao;
import pl.coderslab.warsztat2krajeew04.dao.UsersGroupDao;
import pl.coderslab.warsztat2krajeew04.model.User;
import pl.coderslab.warsztat2krajeew04.model.UsersGroup;

import java.util.List;
import java.util.Scanner;

public class UserController {
    public static void main(String[] args) {
        System.out.println("Welcome to user administration panel");
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
                addUser();
            } else if (input.equals("2")) {
                editUser();
            } else if (input.equals("3")) {
                deleteUser();
            } else {
                System.out.println("Provide a proper menu option!");
            }
        }
        System.out.println("Program has ended!");
    }

    private static void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Eneter userId to delete");
        final int userId = scanner.nextInt();
        try {
            UserDao userDao = new UserDao();
            userDao.delete(userId);
        } catch (Exception e) {
            System.out.println("Invalid delete from database");
        }
    }

    private static void editUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id");
        final int userId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter username");
        final String userName = scanner.nextLine();
        System.out.println("Enter email");
        final String userEmail = scanner.nextLine();
        System.out.println("Enter password");
        final String userPassword = scanner.nextLine();
        System.out.println("Enter userGroupId");
        final int userGroupId = Integer.parseInt(scanner.nextLine());
        try{
            UserDao userDao = new UserDao();
            User user = new User(userId, userName, userEmail, userPassword, userGroupId);
            userDao.update(user);
            System.out.println("user edited!");
        }catch (Exception e ){
            System.out.println("Eroor while editing user");
        }
    }

    private static void addUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username");
        final String username = scanner.nextLine();
        System.out.println("Enter email");
        final String email = scanner.nextLine();
        System.out.println("Enter password");
        final String password = scanner.nextLine();
        System.out.println("Enter userGroupId");
        List<UsersGroup> usersGroups = new UsersGroupDao().findAll();
        for (UsersGroup group : usersGroups) {
            System.out.println("id "+group.getId()+" name: "+ group.getName());
        }
        final int userGroupId = Integer.parseInt(scanner.nextLine());
        User user = new User(username, email, password, userGroupId);
        UserDao userDao = new UserDao();
        if (userDao.create(user) != null) {
            System.out.println("User saved");
        } else {
            System.out.println("Invalid save to database");
        }
    }
}