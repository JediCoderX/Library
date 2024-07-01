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

import library.model.Borrower;
import library.repository.BorrowerRepository;
import library.specification.BorrowerSpecs;

@RestController
@RequestMapping("/borrowers")
public class BorrowerController {

    private final BorrowerRepository repository;

    BorrowerController(BorrowerRepository repository) {
        this.repository = repository;
    }

    // Get all borrowers
    @GetMapping
    Page<Borrower> all(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    // Get borrower by id
    @GetMapping("/{id}")
    Optional<Borrower> one(@PathVariable Long id) {
        return repository.findById(id);
    }

    // Create new borrower
    @PostMapping
    public Borrower saveBorrower(@RequestBody Borrower borrower) {
        return repository.save(borrower);
    }

    // Update borrower info
    @PutMapping("/{id}")
    public Borrower updateBorrower(@PathVariable Long id, @RequestBody Borrower borrowerDetails) {
        return repository.findById(id)
                .map(borrower -> {
                    borrower.setId(borrowerDetails.getId());
                    borrower.setName(borrowerDetails.getName());
                    return repository.save(borrower);
                })
                .orElseGet(() -> {
                    return null;
                });
    }

    // Delete borrower
    @DeleteMapping("/{id}")
    public void deleteBorrower(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // Search Borrower
    @GetMapping("/search")
    public List<Borrower> searchBorrower(@RequestParam(required = false) String name) {

        Specification<Borrower> spec = Specification.where(BorrowerSpecs.hasName(name));

        return repository.findAll(spec);
    }
}
