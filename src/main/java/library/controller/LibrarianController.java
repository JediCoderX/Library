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

import library.model.Librarian;
import library.repository.LibrarianRepository;
import library.specification.LibrarianSpecs;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {

    private final LibrarianRepository repository;

    LibrarianController(LibrarianRepository repository) {
        this.repository = repository;
    }

    // Get all librarians
    @GetMapping
    Page<Librarian> all(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        return repository.findAll(PageRequest.of(page, size));
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

    // Search Librarian
    @GetMapping("/search")
    public List<Librarian> searchLibrarian(@RequestParam(required = false) String name) {

        Specification<Librarian> spec = Specification.where(LibrarianSpecs.hasName(name));

        return repository.findAll(spec);
    }
}
