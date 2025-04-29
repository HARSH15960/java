
// data/BookDatabase.java
package data;

import models.Book;

public class BookDatabase {
    public static Book[] books = new Book[200];
    public static int bookCount = 0;

    static {
        addCategoryBooks("Fiction", "Author F");
        addCategoryBooks("Science", "Author S");
        addCategoryBooks("History", "Author H");
        addCategoryBooks("Biography", "Author B");
        addCategoryBooks("Technology", "Author T");
        addCategoryBooks("Fantasy", "Author Y");
        addCategoryBooks("Philosophy", "Author P");
        addCategoryBooks("Self-help", "Author SH");
        addCategoryBooks("Mystery", "Author M");
        addCategoryBooks("Romance", "Author R");
    }

    private static void addCategoryBooks(String category, String authorPrefix) {
        for (int i = 1; i <= 10; i++) {
            addBook(new Book(bookCount + 1, category + " Book " + i, authorPrefix + i, category));
        }
    }

    public static void addBook(Book book) {
        books[bookCount++] = book;
    }

    public static Book getBookById(int id) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].id == id) return books[i];
        }
        return null;
    }

    public static void listAvailableBooks() {
        for (int i = 0; i < bookCount; i++) {
            Book book = books[i];
            if (!book.isLent) {
                System.out.println(book.id + ". [" + book.category + "] " + book.title + " by " + book.author);
            }
        }
    }

    public static void listCategories() {
        System.out.println("Available Categories:");
        String[] printed = new String[20];
        int count = 0;
        for (int i = 0; i < bookCount; i++) {
            Book b = books[i];
            boolean exists = false;
            for (int j = 0; j < count; j++) {
                if (printed[j].equals(b.category)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                printed[count++] = b.category;
                System.out.println("- " + b.category);
            }
        }
    }

    public static void listBooksByCategory(String category) {
        for (int i = 0; i < bookCount; i++) {
            Book book = books[i];
            if (!book.isLent && book.category.equalsIgnoreCase(category)) {
                System.out.println(book.id + ". " + book.title + " by " + book.author);
            }
        }
    }
}