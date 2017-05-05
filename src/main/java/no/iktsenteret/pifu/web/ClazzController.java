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

import no.iktsenteret.pifu.domain.Clazz;
import no.iktsenteret.pifu.domain.LineItem;
import no.iktsenteret.pifu.domain.Result;
import no.iktsenteret.pifu.domain.User;
import no.iktsenteret.pifu.service.clazz.ClazzService;
import no.iktsenteret.pifu.web.response.ApiResponse;

@RestController
@ExposesResourceFor(Clazz.class)
public class ClazzController {

	private final ClazzService clazzService;

	private final ApiResponse apiResponse;

	public ClazzController(ClazzService clazzService, ApiResponse apiResponse) {
		this.clazzService = clazzService;
		this.apiResponse = apiResponse;
	}

	@GetMapping(value = "/classes")
	public ResponseEntity<?> getClasses(Specification<Clazz> filters, Pageable page, FilterProvider fields,
			HttpServletRequest request) {
		Page<Clazz> clazzes = clazzService.getClasses(filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, clazzes, page))
				.body(apiResponse.collection(Clazz.class, clazzes, fields));
	}

	@GetMapping(value = "/classes/{id}")
	public ResponseEntity<?> getClass(@PathVariable("id") String sourcedId, FilterProvider fields) {
		Clazz clazz = clazzService.getClass(sourcedId);

		return ResponseEntity.ok().body(apiResponse.item(Clazz.class, clazz, fields));
	}

	@GetMapping(value = "/classes/{id}/students")
	public ResponseEntity<?> getStudentsForClass(@PathVariable("id") String sourcedId, Specification<User> filters,
			Pageable page, FilterProvider fields, HttpServletRequest request) {
		Page<User> students = clazzService.getStudentsForClass(sourcedId, filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, students, page))
				.body(apiResponse.collection(User.class, students, fields));
	}

	@GetMapping(value = "/classes/{id}/teachers")
	public ResponseEntity<?> getTeachersForClass(@PathVariable("id") String sourcedId, Specification<User> filters,
			Pageable page, FilterProvider fields, HttpServletRequest request) {
		Page<User> teachers = clazzService.getTeachersForClass(sourcedId, filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, teachers, page))
				.body(apiResponse.collection(User.class, teachers, fields));
	}

	@GetMapping(value = "/classes/{id}/results")
	public ResponseEntity<?> getResultsForClass(@PathVariable("id") String sourcedId, Specification<Result> filters,
			Pageable page, FilterProvider fields, HttpServletRequest request) {
		Page<Result> results = clazzService.getResultsForClass(sourcedId, filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, results, page))
				.body(apiResponse.collection(Result.class, results, fields));
	}

	@GetMapping(value = "/classes/{id}/lineItems")
	public ResponseEntity<?> getLineItemsForClass(@PathVariable("id") String sourcedId, Specification<LineItem> filters,
			Pageable page, FilterProvider fields, HttpServletRequest request) {
		Page<LineItem> lineItems = clazzService.getLineItemsForClass(sourcedId, filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, lineItems, page))
				.body(apiResponse.collection(LineItem.class, lineItems, fields));
	}

	@GetMapping(value = "/classes/{class_id}/lineItems/{li_id}/results")
	public ResponseEntity<?> getResultsForLineItemForClass(@PathVariable("class_id") String clazzSourcedId,
			@PathVariable("li_id") String lineItemSourcedId, Specification<Result> filters, Pageable page,
			FilterProvider fields, HttpServletRequest request) {
		Page<Result> results = clazzService.getResultsForLineItemForClass(clazzSourcedId, lineItemSourcedId, filters,
				page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, results, page))
				.body(apiResponse.collection(Result.class, results, fields));
	}

	@GetMapping(value = "/classes/{class_id}/students/{student_id}/results")
	public ResponseEntity<?> getResultsForStudentsForClass(@PathVariable("class_id") String clazzSourcedId,
			@PathVariable("student_id") String studentSourcedId, Specification<Result> filters, Pageable page,
			FilterProvider fields, HttpServletRequest request) {
		Page<Result> results = clazzService.getResultsForStudentsForClass(clazzSourcedId, studentSourcedId, filters,
				page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, results, page))
				.body(apiResponse.collection(Result.class, results, fields));
	}
}