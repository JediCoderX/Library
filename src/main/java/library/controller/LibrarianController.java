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

import library.model.Librarian;
import library.repository.LibrarianRepository;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {

    private final LibrarianRepository repository;

    LibrarianController(LibrarianRepository repository) {
        this.repository = repository;
    }

    // Get all librarians
    @GetMapping
    List<Librarian> all() {
        return repository.findAll();
    }

    // Get librarian by id
    @GetMapping("/{id}")
    Optional<Librarian> one(@PathVariable Long id) {
        return repository.findById(id);
    }

    // Create new librarian
    @PostMapping
    public Librarian saveLibrarian(@RequestBody Librarian librarian) {
        return repository.save(librarian);
    }

    // Update librarian info
    @PutMapping("/{id}")
    public Librarian updateLibrarian(@PathVariable Long id, @RequestBody Librarian librarianDetails) {
        return repository.findById(id)
                .map(librarian -> {
                    librarian.setId(librarianDetails.getId());
                    librarian.setName(librarianDetails.getName());
                    return repository.save(librarian);
                })
                .orElseGet(() -> {
                    return null;
                });
    }

    // Delete librarian
    @DeleteMapping("/{id}")
    public void deleteLibrarian(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
