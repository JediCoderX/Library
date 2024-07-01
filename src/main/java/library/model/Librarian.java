package library.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Librarian {

    // Primary key with auto-increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // One-to-many relationship BookBorrowing
    @OneToMany(mappedBy = "librarian")
    private List<BorrowRecord> bookBorrowing = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BorrowRecord> getBookBorrowing() {
        return bookBorrowing;
    }

    public void setBookBorrowing(List<BorrowRecord> bookBorrowing) {
        this.bookBorrowing = bookBorrowing;
    }

}
