package library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.model.Librarian;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {

}
