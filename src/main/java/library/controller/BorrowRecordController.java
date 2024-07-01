package library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import library.model.BorrowRecord;
import library.repository.BorrowRecordRepository;

@RestController
@RequestMapping("/borrow-records")
public class BorrowRecordController {

    private final BorrowRecordRepository repository;

    BorrowRecordController(BorrowRecordRepository repository) {
        this.repository = repository;
    }

    // Get all records
    @GetMapping
    List<BorrowRecord> all() {
        return repository.findAll();
    }

    // Get record by id
    @GetMapping("/{id}")
    Optional<BorrowRecord> one(@PathVariable Long id) {
        return repository.findById(id);
    }

    // Create new record
    @PostMapping
    public BorrowRecord saveRecords(@RequestBody BorrowRecord newRecord) {
        return repository.save(newRecord);
    }

    // Delete record
    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
