package services;

import data.BookDatabase;
import data.UserDatabase;
import models.Book;
import models.User;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AdminService {
    Scanner sc = new Scanner(System.in);

    public void showAdminMenu() {
        while (true) {
            System.out.println("\n📘 Admin Dashboard:");
            System.out.println("1. View All Books");
            System.out.println("2. View Lent Books with Borrowers");
            System.out.println("3. View All Users");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewAllBooks();
                    break;
                case 2:
                    viewLentBooks();
                    break;
                case 3:
                    viewAllUsers();
                    break;
                case 4:
                    System.out.println("🔓 Admin logged out.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void viewAllBooks() {
        System.out.println("\n📚 All Books:");
        for (int i = 0; i < BookDatabase.bookCount; i++) {
            Book b = BookDatabase.books[i];
            System.out.println(b.id + ". " + b.title + " by " + b.author + (b.isLent ? " (Lent)" : " (Available)"));
        }
    }

    private void viewLentBooks() {
        System.out.println("\n📖 Lent Books with Borrower Info & Fines:");
        for (int i = 0; i < BookDatabase.bookCount; i++) {
            Book b = BookDatabase.books[i];
            if (b.isLent) {
                User u = UserDatabase.getUserById(b.lentToUserId);
                long overdueDays = ChronoUnit.DAYS.between(b.lendDate, LocalDate.now()) - 7;
                int fine = (int) Math.max(0, overdueDays * 5);
                System.out.println("📘 " + b.title + " -> Borrowed by: " + u.name + " (" + u.email + ") | Fine: ₹" + fine);
            }
        }
    }

    private void viewAllUsers() {
        System.out.println("\n👥 All Registered Users:");
        for (int i = 0; i < UserDatabase.userCount; i++) {
            User u = UserDatabase.users[i];
            System.out.println(u.id + ". " + u.name + " | " + u.email);
        }
    }
}
