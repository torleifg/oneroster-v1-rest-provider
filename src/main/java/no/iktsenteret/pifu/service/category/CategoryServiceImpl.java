package no.iktsenteret.pifu.service.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import no.iktsenteret.pifu.domain.Category;
import no.iktsenteret.pifu.web.exception.NotFoundException;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<Category> getCategories(Specification<Category> filters, Pageable page) {
		Page<Category> categories = categoryRepository.findAll(filters, page);

		return categories;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Category getCategory(String sourcedId) {
		Category category = categoryRepository.findOne(sourcedId);

		if (category == null)
			throw new NotFoundException("Category not found: " + sourcedId);

		return category;
	}
}
