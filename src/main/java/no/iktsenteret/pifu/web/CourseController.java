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
import no.iktsenteret.pifu.domain.Course;
import no.iktsenteret.pifu.service.course.CourseService;
import no.iktsenteret.pifu.web.response.ApiResponse;

@RestController
@ExposesResourceFor(Course.class)
public class CourseController {

	private final CourseService courseService;

	private final ApiResponse apiResponse;

	public CourseController(CourseService courseService, ApiResponse apiResponse) {
		this.courseService = courseService;
		this.apiResponse = apiResponse;
	}

	@GetMapping(value = "/courses")
	public ResponseEntity<?> getCourses(Specification<Course> filters, Pageable page, FilterProvider fields,
			HttpServletRequest request) {
		Page<Course> courses = courseService.getCourses(filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, courses, page))
				.body(apiResponse.collection(Course.class, courses, fields));
	}

	@GetMapping(value = "/courses/{id}")
	public ResponseEntity<?> getCourse(@PathVariable("id") String sourcedId, FilterProvider fields) {
		Course course = courseService.getCourse(sourcedId);

		return ResponseEntity.ok().body(apiResponse.item(Course.class, course, fields));
	}

	@GetMapping(value = "/courses/{id}/classes")
	public ResponseEntity<?> getClassesForCourse(@PathVariable("id") String sourcedId, Specification<Clazz> filters,
			Pageable page, FilterProvider fields, HttpServletRequest request) {
		Page<Clazz> clazzes = courseService.getClassesforCourse(sourcedId, filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, clazzes, page))
				.body(apiResponse.collection(Clazz.class, clazzes, fields));
	}
}