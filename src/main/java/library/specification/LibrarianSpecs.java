package library.specification;

import org.springframework.data.jpa.domain.Specification;

import library.model.Librarian;

public class LibrarianSpecs {

    public static Specification<Librarian> hasName(String name) {
        return (root, query, builder) -> name == null ? null
                : builder.like(builder.lower(root.get("name")), "%" + name + "%");
    }
}
