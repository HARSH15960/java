package services;

import data.BookDatabase;
import data.UserDatabase;
import models.Book;
import models.User;
import java.util.Scanner;

public class AdminService {
    Scanner sc = new Scanner(System.in);

    public void showAdminMenu() {
        while (true) {
            System.out.println("\n📘 Admin Dashboard:");
            System.out.println("1. View All Books");
            System.out.println("2. View All Users");
            System.out.println("3. Add a Book");
            System.out.println("4. Delete a Book");
            System.out.println("5. Search User by Email");
            System.out.println("6. View Lent Books Summary");
            System.out.println("7. Logout");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // buffer clear

            switch (choice) {
                case 1:
                    viewAllBooks();
                    break;
                case 2:
                    viewAllUsers();
                    break;
                case 3:
                    addBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    searchUser();
                    break;
                case 6:
                    lentSummary();
                    break;
                case 7:
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

    private void viewAllUsers() {
        System.out.println("\n👥 All Registered Users:");
        for (int i = 0; i < UserDatabase.userCount; i++) {
            User u = UserDatabase.users[i];
            System.out.println(u.id + ". " + u.name + " | " + u.email);
        }
    }

    private void addBook() {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        System.out.print("Enter author name: ");
        String author = sc.nextLine();
        System.out.print("Enter category: ");
        String category = sc.nextLine();

        Book newBook = new Book(BookDatabase.nextBookId++, title, author, category);
        BookDatabase.addBook(newBook);
        System.out.println("✅ Book added successfully!");
    }

    private void deleteBook() {
        viewAllBooks();
        System.out.print("Enter Book ID to delete: ");
        int id = sc.nextInt();
        boolean deleted = BookDatabase.deleteBook(id);
        if (deleted) {
            System.out.println("✅ Book deleted.");
        } else {
            System.out.println("❌ Book not found.");
        }
    }

    private void searchUser() {
        System.out.print("Enter email to search: ");
        String email = sc.nextLine();
        User user = UserDatabase.findUserByEmail(email);
        if (user != null) {
            System.out.println("Found: " + user.name + " | " + user.email);
        } else {
            System.out.println("❌ User not found.");
        }
    }

    private void lentSummary() {
        System.out.println("📋 Lent Books Summary:");
        for (Book b : BookDatabase.books) {
            if (b != null && b.isLent) {
                User u = UserDatabase.getUserById(b.lentToUserId);
                System.out.println("📖 " + b.title + " → " + u.name + " (" + u.email + ")");
            }
        }
    }
}
