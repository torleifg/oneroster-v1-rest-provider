package no.iktsenteret.pifu.service.org;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import no.iktsenteret.pifu.domain.AcademicSession;
import no.iktsenteret.pifu.domain.Clazz;
import no.iktsenteret.pifu.domain.Course;
import no.iktsenteret.pifu.domain.Enrollment;
import no.iktsenteret.pifu.domain.Org;
import no.iktsenteret.pifu.domain.User;

public interface OrgService {

	Page<Org> getOrgs(Specification<Org> filters, Pageable page);

	Org getOrg(String sourcedId);

	Page<Org> getSchools(Specification<Org> filters, Pageable page);

	Org getSchool(String sourcedId);

	Page<Course> getCoursesForSchool(String sourcedId, Specification<Course> filters, Pageable page);

	Page<Clazz> getClassesForSchool(String sourcedId, Specification<Clazz> filters, Pageable page);

	Page<Enrollment> getEnrollmentsForClassInSchool(String schoolSourcedId, String clazzSourcedId,
			Specification<Enrollment> filters, Pageable page);

	Page<User> getStudentsForClassInSchool(String schoolSourcedId, String clazzSourcedId, Specification<User> filters,
			Pageable page);

	Page<User> getTeachersForClassInSchool(String schoolSourcedId, String clazzSourcedId, Specification<User> filters,
			Pageable page);

	Page<Enrollment> getEnrollmentsForSchool(String sourcedId, Specification<Enrollment> filters, Pageable page);

	Page<User> getStudentsForSchool(String sourcedId, Specification<User> filters, Pageable page);

	Page<User> getTeachersForSchool(String sourcedId, Specification<User> filters, Pageable page);

	Page<AcademicSession> getTermsForSchool(String sourcedId, Specification<AcademicSession> filters, Pageable page);
}
