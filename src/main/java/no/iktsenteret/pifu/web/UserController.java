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
import no.iktsenteret.pifu.domain.User;
import no.iktsenteret.pifu.service.user.UserService;
import no.iktsenteret.pifu.web.response.ApiResponse;

@RestController
@ExposesResourceFor(User.class)
public class UserController {

	private final UserService userService;

	private final ApiResponse apiResponse;

	public UserController(UserService userService, ApiResponse apiResponse) {
		this.userService = userService;
		this.apiResponse = apiResponse;
	}

	@GetMapping(value = "/users")
	public ResponseEntity<?> getUsers(Specification<User> filters, Pageable page, FilterProvider fields,
			HttpServletRequest request) {
		Page<User> users = userService.getUsers(filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, users, page))
				.body(apiResponse.collection(User.class, users, fields));
	}

	@GetMapping(value = "/users/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") String sourcedId, FilterProvider fields) {
		User user = userService.getUser(sourcedId);

		return ResponseEntity.ok().body(apiResponse.item(User.class, user, fields));
	}

	@GetMapping(value = "/students")
	public ResponseEntity<?> getStudents(Specification<User> filters, Pageable page, FilterProvider fields,
			HttpServletRequest request) {
		Page<User> students = userService.getStudents(filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, students, page))
				.body(apiResponse.collection(User.class, students, fields));
	}

	@GetMapping(value = "/students/{id}")
	public ResponseEntity<?> getStudent(@PathVariable("id") String sourcedId, FilterProvider fields) {
		User student = userService.getStudent(sourcedId);

		return ResponseEntity.ok().body(apiResponse.item(User.class, student, fields));
	}

	@GetMapping(value = "/teachers")
	public ResponseEntity<?> getTeachers(Specification<User> filters, Pageable page, FilterProvider fields,
			HttpServletRequest request) {
		Page<User> teachers = userService.getTeachers(filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, teachers, page))
				.body(apiResponse.collection(User.class, teachers, fields));
	}

	@GetMapping(value = "/teachers/{id}")
	public ResponseEntity<?> getTeacher(@PathVariable("id") String sourcedId, FilterProvider fields) {
		User teacher = userService.getTeacher(sourcedId);

		return ResponseEntity.ok().body(apiResponse.item(User.class, teacher, fields));
	}

	@GetMapping(value = "/students/{id}/classes")
	public ResponseEntity<?> getClassesForStudent(@PathVariable("id") String sourcedId, Specification<Clazz> filters,
			Pageable page, FilterProvider fields, HttpServletRequest request) {
		Page<Clazz> clazzes = userService.getClassesForStudent(sourcedId, filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, clazzes, page))
				.body(apiResponse.collection(Clazz.class, clazzes, fields));
	}

	@GetMapping(value = "/teachers/{id}/classes")
	public ResponseEntity<?> getClassesForTeacher(@PathVariable("id") String sourcedId, Specification<Clazz> filters,
			Pageable page, FilterProvider fields, HttpServletRequest request) {
		Page<Clazz> clazzes = userService.getClassesForTeacher(sourcedId, filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, clazzes, page))
				.body(apiResponse.collection(Clazz.class, clazzes, fields));
	}
}