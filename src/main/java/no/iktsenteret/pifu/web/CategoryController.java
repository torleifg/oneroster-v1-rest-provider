package no.iktsenteret.pifu.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;

import no.iktsenteret.pifu.domain.Category;
import no.iktsenteret.pifu.service.category.CategoryService;
import no.iktsenteret.pifu.web.response.ApiResponse;

@RestController
@ExposesResourceFor(Category.class)
public class CategoryController {

	private final CategoryService categoryService;

	private final ApiResponse apiResponse;

	public CategoryController(CategoryService categoryService, ApiResponse apiResponse) {
		this.categoryService = categoryService;
		this.apiResponse = apiResponse;
	}

	@GetMapping(value = "/categories")
	public ResponseEntity<?> getCategories(Pageable page, Specification<Category> filters, FilterProvider fields,
			HttpServletRequest request) {
		Page<Category> categories = categoryService.getCategories(filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, categories, page))
				.body(apiResponse.collection(Category.class, categories, fields));
	}

	@GetMapping(value = "/categories/{id}")
	public ResponseEntity<?> getCategory(@PathVariable("id") String sourcedId, FilterProvider fields) {
		Category category = categoryService.getCategory(sourcedId);

		return ResponseEntity.ok().body(apiResponse.item(Category.class, category, fields));
	}
}
