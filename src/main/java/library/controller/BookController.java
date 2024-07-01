package library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import library.model.Book;
import library.repository.BookRepository;
import library.specification.BookSpecs;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository repository;

    BookController(BookRepository repository) {
        this.repository = repository;
    }

    // Get all books
    @GetMapping
    Page<Book> all(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        return repository.findAll(PageRequest.of(page, size));
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

    // Search book
    @GetMapping("/search")
    public List<Book> searchBook(@RequestParam(required = false) String title,
            @RequestParam(required = false) String author, @RequestParam(required = false) String isbn) {

        Specification<Book> spec = Specification.where(BookSpecs.hasTitle(title)).and(BookSpecs.hasAuthor(author))
                .and(BookSpecs.hasIsbn(isbn));
        return repository.findAll(spec);
    }
}
