// models/Book.java
package models;

import java.time.LocalDate;

public class Book {
    public int id;
    public String title;
    public String author;
    public boolean isLent;
    public int lentToUserId;
    public String category;
    public LocalDate lendDate;  // <-- ADD THIS LINE

    public Book(int id, String title, String author, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isLent = false;
        this.lentToUserId = -1;
        this.lendDate = null;  // <-- INITIALIZE TO null
    }
}
