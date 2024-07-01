package library.specification;

import org.springframework.data.jpa.domain.Specification;

import library.model.Book;

public class BookSpecs {

    public static Specification<Book> hasTitle(String title) {
        return (root, query, builder) -> title == null ? null
                : builder.like(builder.lower(root.get("title")), "%" + title + "%");
    }

    public static Specification<Book> hasAuthor(String author) {
        return (root, query, builder) -> author == null ? null
                : builder.like(builder.lower(root.get("author")), "%" + author + "%");
    }

    public static Specification<Book> hasIsbn(String isbn) {
        return (root, query, builder) -> isbn == null ? null : builder.equal(root.get("isbn"), isbn);
    }
}
