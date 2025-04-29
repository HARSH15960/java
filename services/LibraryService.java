// services/LibraryService.java
package services;

import data.BookDatabase;
import data.UserDatabase;
import models.Book;
import models.User;

import java.util.Scanner;

public class LibraryService {
    Scanner sc = new Scanner(System.in);

    public void menu(int userId) {
        while (true) {
            System.out.println("\n1. View Available Books\n2. Lend a Book\n3. My Profile\n4. Logout");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    System.out.println("\nDo you want to view books by category? (yes/no): ");
                    String byCat = sc.nextLine();
                    if (byCat.equalsIgnoreCase("yes")) {
                        BookDatabase.listCategories();
                        System.out.print("Enter category name: ");
                        String category = sc.nextLine();
                        BookDatabase.listBooksByCategory(category);
                    } else {
                        BookDatabase.listAvailableBooks();
                    }
                    break;

                case 2:
                    BookDatabase.listAvailableBooks();
                    System.out.print("Enter Book ID to lend: ");
                    int bookId = sc.nextInt();
                    Book book = BookDatabase.getBookById(bookId);
                    if (book != null && !book.isLent) {
                        book.isLent = true;
                        book.lentToUserId = userId;
                        System.out.println("Book lent successfully.");
                    } else {
                        System.out.println("Book not available.");
                    }
                    break;

                case 3:
                    User user = UserDatabase.getUserById(userId);
                    System.out.println("Name: " + user.name);
                    System.out.println("Email: " + user.email);
                    System.out.println("Books Lent:");
                    for (Book b : BookDatabase.books) {
                        if (b != null && b.lentToUserId == userId) {
                            System.out.println("📖 " + b.title + " (" + b.category + ")");
                        }
                    }
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
