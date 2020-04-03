package pl.coderslab.warsztat2krajeew04.controller;

import pl.coderslab.warsztat2krajeew04.dao.UserDao;
import pl.coderslab.warsztat2krajeew04.dao.UsersGroupDao;
import pl.coderslab.warsztat2krajeew04.model.User;
import pl.coderslab.warsztat2krajeew04.model.UsersGroup;

import java.util.List;
import java.util.Scanner;

public class UserGroupController {
    public static void main(String[] args) {
        System.out.println("Welcome to userGroup administration panel");
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
                addUserGroup();
            } else if (input.equals("2")) {
                editUserGroup();
            } else if (input.equals("3")) {
                deleteUserGroup();
            } else {
                System.out.println("Provide a proper menu option!");
            }
        }
        System.out.println("Program has ended!");
    }

    private static void deleteUserGroup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Eneter userGroupId to delete");
        final int userGroupId = scanner.nextInt();
        try {
            UsersGroupDao usersGroupDao = new UsersGroupDao();
            usersGroupDao.delete(userGroupId);
        } catch (Exception e) {
            System.out.println("Invalid delete from database");
        }
    }

    private static void editUserGroup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id");
        final int userGroupId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter name");
        final String userGroupName = scanner.nextLine();
        try {
            UsersGroupDao user = new UsersGroupDao();
            UsersGroup usersGroup = new UsersGroup(userGroupId, userGroupName);
            user.update(usersGroup);
            System.out.println("userGroup edited!");
        } catch (Exception e) {
            System.out.println("Eroor while editing user");
        }
    }

    private static void addUserGroup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name");
        final String userGroupName = scanner.nextLine();
        UsersGroup userGroup = new UsersGroup(userGroupName);
        UsersGroupDao usersGroupDao = new UsersGroupDao();
        if (usersGroupDao.create(userGroup) != null) {
            System.out.println("User saved");
        } else {
            System.out.println("Invalid save to database");
        }
    }
}