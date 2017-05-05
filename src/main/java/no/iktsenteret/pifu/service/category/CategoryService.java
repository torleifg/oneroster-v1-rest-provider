package no.iktsenteret.pifu.service.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import no.iktsenteret.pifu.domain.Category;

public interface CategoryService {

	Page<Category> getCategories(Specification<Category> filters, Pageable page);

	Category getCategory(String sourcedId);
}
