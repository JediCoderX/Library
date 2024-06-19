package library.model;

import jakarta.persistence.*;

@Entity
public class BookBorrowing {

    // Primary key with auto-increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-one relationship with Book
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    // Many-to-one relationship with Borrower
    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private Borrower borrower;

    // Many-to-one relationship with Librarian
    @ManyToOne
    @JoinColumn(name = "librarian_id")
    private Librarian librarian;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

}
