package services;

import data.UserDatabase;
import models.User;
import java.util.Scanner;

public class AuthService {
    Scanner sc = new Scanner(System.in);

    public void register() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();

        if (UserDatabase.findUserByEmail(email) != null) {
            System.out.println("User already exists!");
            return;
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        User newUser = new User(UserDatabase.userCount + 1, name, email, password);
        UserDatabase.addUser(newUser);
        System.out.println("Registration successful!");
    }

    public int login() {
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        User user = UserDatabase.findUserByEmail(email);
        if (user != null && user.password.equals(password)) {
            System.out.println("Login successful!");
            return user.id;
        }

        System.out.println("Invalid credentials.");
        return -1;
    }
}
