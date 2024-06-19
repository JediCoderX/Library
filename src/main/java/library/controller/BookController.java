package library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import library.model.Book;
import library.repository.BookRepository;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository repository;

    BookController(BookRepository repository) {
        this.repository = repository;
    }

    // Get all books
    @GetMapping
    List<Book> all() {
        return repository.findAll();
    }

    // Get book by id
    @GetMapping("/{id}")
    Optional<Book> one(@PathVariable Long id) {
        return repository.findById(id);
    }

    // Create new book
    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return repository.save(book);
    }

    // Update book info
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return repository.findById(id)
                .map(book -> {
                    book.setId(bookDetails.getId());
                    book.setTitle(bookDetails.getTitle());
                    book.setAuthor(bookDetails.getAuthor());
                    book.setIsbn(bookDetails.getIsbn());
                    return repository.save(book);
                })
                .orElseGet(() -> {
                    return null;
                });
    }

    // Delete book
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
