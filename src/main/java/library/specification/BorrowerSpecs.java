package library.specification;

import org.springframework.data.jpa.domain.Specification;

import library.model.Borrower;

public class BorrowerSpecs {

    public static Specification<Borrower> hasName(String name) {
        return (root, query, builder) -> name == null ? null
                : builder.like(builder.lower(root.get("name")), "%" + name + "%");
    }
}
