// data/BookDatabase.java
package data;
import models.Book;

public class BookDatabase{
    public static Book[] books = new Book[100];
    public static int bookCount = 0;

    static {
        addBook(new Book(1, "The Alchemist", "Paulo Coelho"));
        addBook(new Book(2, "1984", "George Orwell"));
        addBook(new Book(3, "Java Basics", "Herbert Schildt"));
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
                System.out.println(book.id + ". " + book.title + " by " + book.author);
            }
        }
    }
}
