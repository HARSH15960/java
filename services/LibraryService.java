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
            System.out.println("\n📚 User Dashboard:");
            System.out.println("1. View Available Books");
            System.out.println("2. Lend a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. My Profile");
            System.out.println("5. Search Book by Title");
            System.out.println("6. Logout");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    BookDatabase.listAvailableBooks();
                    break;

                case 2:
                    int lentCount = 0;
                    for (Book b : BookDatabase.books) {
                        if (b != null && b.lentToUserId == userId) lentCount++;
                    }

                    if (lentCount >= 3) {
                        System.out.println("❌ You cannot lend more than 3 books!");
                        break;
                    }

                    BookDatabase.listAvailableBooks();
                    System.out.print("Enter Book ID to lend: ");
                    int bookId = sc.nextInt();
                    Book book = BookDatabase.getBookById(bookId);
                    if (book != null && !book.isLent) {
                        book.isLent = true;
                        book.lentToUserId = userId;
                        System.out.println("✅ Book lent successfully.");
                    } else {
                        System.out.println("❌ Book not available.");
                    }
                    break;

                case 3:
                    System.out.println("📖 Your Lent Books:");
                    for (Book b : BookDatabase.books) {
                        if (b != null && b.lentToUserId == userId) {
                            System.out.println(b.id + ". " + b.title);
                        }
                    }
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    Book returnBook = BookDatabase.getBookById(returnId);
                    if (returnBook != null && returnBook.lentToUserId == userId) {
                        returnBook.isLent = false;
                        returnBook.lentToUserId = -1;
                        System.out.println("✅ Book returned successfully.");
                    } else {
                        System.out.println("❌ Invalid return.");
                    }
                    break;

                case 4:
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

                case 5:
                    System.out.print("Enter book title to search: ");
                    String title = sc.nextLine().toLowerCase();
                    boolean found = false;
                    for (Book b : BookDatabase.books) {
                        if (b != null && b.title.toLowerCase().contains(title)) {
                            System.out.println("📘 " + b.title + " by " + b.author + (b.isLent ? " (Lent)" : " (Available)"));
                            found = true;
                        }
                    }
                    if (!found) System.out.println("No books found.");
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
