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

import no.iktsenteret.pifu.domain.AcademicSession;
import no.iktsenteret.pifu.domain.Clazz;
import no.iktsenteret.pifu.domain.Course;
import no.iktsenteret.pifu.domain.Enrollment;
import no.iktsenteret.pifu.domain.Org;
import no.iktsenteret.pifu.domain.User;
import no.iktsenteret.pifu.service.org.OrgService;
import no.iktsenteret.pifu.web.response.ApiResponse;

@RestController
@ExposesResourceFor(Org.class)
public class OrgController {

	private final OrgService orgService;

	private final ApiResponse apiResponse;

	public OrgController(OrgService orgService, ApiResponse apiResponse) {
		this.orgService = orgService;
		this.apiResponse = apiResponse;
	}

	@GetMapping(value = "/orgs")
	public ResponseEntity<?> getOrgs(Specification<Org> filters, Pageable page, FilterProvider fields,
			HttpServletRequest request) {
		Page<Org> orgs = orgService.getOrgs(filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, orgs, page))
				.body(apiResponse.collection(Org.class, orgs, fields));
	}

	@GetMapping(value = "/orgs/{id}")
	public ResponseEntity<?> getOrg(@PathVariable("id") String sourcedId, FilterProvider fields) {
		Org org = orgService.getOrg(sourcedId);

		return ResponseEntity.ok().body(apiResponse.item(Org.class, org, fields));
	}

	@GetMapping(value = "/schools")
	public ResponseEntity<?> getSchools(Specification<Org> filters, Pageable page, FilterProvider fields,
			HttpServletRequest request) {
		Page<Org> schools = orgService.getSchools(filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, schools, page))
				.body(apiResponse.collection(Org.class, schools, fields));
	}

	@GetMapping(value = "/schools/{id}")
	public ResponseEntity<?> getSchool(@PathVariable("id") String sourcedId, FilterProvider fields) {
		Org school = orgService.getSchool(sourcedId);

		return ResponseEntity.ok().body(apiResponse.item(Org.class, school, fields));
	}

	@GetMapping(value = "/schools/{id}/courses")
	public ResponseEntity<?> getCoursesForSchool(@PathVariable("id") String sourcedId, Specification<Course> filters,
			Pageable page, FilterProvider fields, HttpServletRequest request) {
		Page<Course> courses = orgService.getCoursesForSchool(sourcedId, filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, courses, page))
				.body(apiResponse.collection(Course.class, courses, fields));
	}

	@GetMapping(value = "/schools/{id}/classes")
	public ResponseEntity<?> getClassesForSchool(@PathVariable("id") String sourcedId, Specification<Clazz> filters,
			Pageable page, FilterProvider fields, HttpServletRequest request) {
		Page<Clazz> clazzes = orgService.getClassesForSchool(sourcedId, filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, clazzes, page))
				.body(apiResponse.collection(Clazz.class, clazzes, fields));
	}

	@GetMapping(value = "/schools/{school_id}/classes/{class_id}/enrollments")
	public ResponseEntity<?> getEnrollmentsForClassInSchool(@PathVariable("school_id") String schoolSourcedId,
			@PathVariable("class_id") String clazzSourcedId, Specification<Enrollment> filters, Pageable page,
			FilterProvider fields, HttpServletRequest request) {
		Page<Enrollment> enrollments = orgService.getEnrollmentsForClassInSchool(schoolSourcedId, clazzSourcedId,
				filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, enrollments, page))
				.body(apiResponse.collection(Enrollment.class, enrollments, fields));
	}

	@GetMapping(value = "/schools/{school_id}/classes/{class_id}/students")
	public ResponseEntity<?> getStudentsForForClassInSchool(@PathVariable("school_id") String schoolSourcedId,
			@PathVariable("class_id") String clazzSourcedId, Specification<User> filters, Pageable page,
			FilterProvider fields, HttpServletRequest request) {
		Page<User> students = orgService.getStudentsForClassInSchool(schoolSourcedId, clazzSourcedId, filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, students, page))
				.body(apiResponse.collection(User.class, students, fields));
	}

	@GetMapping(value = "/schools/{school_id}/classes/{class_id}/teachers")
	public ResponseEntity<?> getTeachersForForClassInSchool(@PathVariable("school_id") String schoolSourcedId,
			@PathVariable("class_id") String clazzSourcedId, Specification<User> filters, Pageable page,
			FilterProvider fields, HttpServletRequest request) {
		Page<User> teachers = orgService.getTeachersForClassInSchool(schoolSourcedId, clazzSourcedId, filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, teachers, page))
				.body(apiResponse.collection(User.class, teachers, fields));
	}

	@GetMapping(value = "/schools/{id}/enrollments")
	public ResponseEntity<?> getEnrollmentsForSchool(@PathVariable("id") String sourcedId,
			Specification<Enrollment> filters, Pageable page, FilterProvider fields, HttpServletRequest request) {
		Page<Enrollment> enrollments = orgService.getEnrollmentsForSchool(sourcedId, filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, enrollments, page))
				.body(apiResponse.collection(Enrollment.class, enrollments, fields));
	}

	@GetMapping(value = "/schools/{id}/students")
	public ResponseEntity<?> getStudentsForSchool(@PathVariable("id") String sourcedId, Specification<User> filters,
			Pageable page, FilterProvider fields, HttpServletRequest request) {
		Page<User> students = orgService.getStudentsForSchool(sourcedId, filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, students, page))
				.body(apiResponse.collection(User.class, students, fields));
	}

	@GetMapping(value = "/schools/{id}/teachers")
	public ResponseEntity<?> getTeachersForSchool(@PathVariable("id") String sourcedId, Specification<User> filters,
			Pageable page, FilterProvider fields, HttpServletRequest request) {
		Page<User> teachers = orgService.getTeachersForSchool(sourcedId, filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, teachers, page))
				.body(apiResponse.collection(User.class, teachers, fields));
	}

	@GetMapping(value = "/schools/{id}/terms")
	public ResponseEntity<?> getTermsForSchool(@PathVariable("id") String sourcedId,
			Specification<AcademicSession> filters, Pageable page, FilterProvider fields, HttpServletRequest request) {
		Page<AcademicSession> terms = orgService.getTermsForSchool(sourcedId, filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, terms, page))
				.body(apiResponse.collection(AcademicSession.class, terms, fields));
	}
}