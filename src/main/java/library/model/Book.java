package library.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Book {

    // Primary key with auto-increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;

    // One-to-many relationship BookBorrowing
    @OneToMany(mappedBy = "book")
    private List<BookBorrowing> bookBorrowing = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<BookBorrowing> getBookBorrowing() {
        return bookBorrowing;
    }

    public void setBookBorrowing(List<BookBorrowing> bookBorrowing) {
        this.bookBorrowing = bookBorrowing;
    }

}
