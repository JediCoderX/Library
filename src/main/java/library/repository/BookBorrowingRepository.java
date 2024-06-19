package library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.model.BookBorrowing;

@Repository
public interface BookBorrowingRepository extends JpaRepository<BookBorrowing, Long> {

}
