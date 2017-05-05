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

import no.iktsenteret.pifu.domain.Enrollment;
import no.iktsenteret.pifu.service.enrollment.EnrollmentService;
import no.iktsenteret.pifu.web.response.ApiResponse;

@RestController
@ExposesResourceFor(Enrollment.class)
public class EnrollmentController {

	private final EnrollmentService enrollmentService;

	private final ApiResponse apiResponse;

	public EnrollmentController(EnrollmentService enrollmentService, ApiResponse apiResponse) {
		this.enrollmentService = enrollmentService;
		this.apiResponse = apiResponse;
	}

	@GetMapping(value = "/enrollments")
	public ResponseEntity<?> getEnrollments(Specification<Enrollment> filters, Pageable page, FilterProvider fields,
			HttpServletRequest request) {
		Page<Enrollment> enrollments = enrollmentService.getEnrollments(filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, enrollments, page))
				.body(apiResponse.collection(Enrollment.class, enrollments, fields));
	}

	@GetMapping(value = "/enrollments/{id}")
	public ResponseEntity<?> getEnrollment(@PathVariable("id") String sourcedId, FilterProvider fields) {
		Enrollment enrollment = enrollmentService.getEnrollment(sourcedId);

		return ResponseEntity.ok().body(apiResponse.item(Enrollment.class, enrollment, fields));
	}
}
