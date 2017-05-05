package no.iktsenteret.pifu.service.org;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import no.iktsenteret.pifu.domain.AcademicSession;
import no.iktsenteret.pifu.domain.Clazz;
import no.iktsenteret.pifu.domain.Course;
import no.iktsenteret.pifu.domain.Enrollment;
import no.iktsenteret.pifu.domain.Org;
import no.iktsenteret.pifu.domain.User;
import no.iktsenteret.pifu.domain.vocab.OrgType;
import no.iktsenteret.pifu.domain.vocab.Role;
import no.iktsenteret.pifu.service.academicsession.AcademicSessionRepository;
import no.iktsenteret.pifu.service.academicsession.AcademicSessionSpecs;
import no.iktsenteret.pifu.service.clazz.ClazzRepository;
import no.iktsenteret.pifu.service.clazz.ClazzSpecs;
import no.iktsenteret.pifu.service.course.CourseRepository;
import no.iktsenteret.pifu.service.course.CourseSpecs;
import no.iktsenteret.pifu.service.enrollment.EnrollmentRepository;
import no.iktsenteret.pifu.service.enrollment.EnrollmentSpecs;
import no.iktsenteret.pifu.service.user.UserRepository;
import no.iktsenteret.pifu.service.user.UserSpecs;
import no.iktsenteret.pifu.web.exception.NotFoundException;

@Service
@Transactional(readOnly = true)
public class OrgServiceImpl implements OrgService {

	private final OrgRepository orgRepository;

	private final CourseRepository courseRepository;

	private final ClazzRepository clazzRepository;

	private final EnrollmentRepository enrollmentRepository;

	private final UserRepository userRepository;

	private final AcademicSessionRepository academicSessionRepository;

	public OrgServiceImpl(OrgRepository orgRepository, CourseRepository courseRepository,
			ClazzRepository clazzRepository, EnrollmentRepository enrollmentRepository, UserRepository userRepository,
			AcademicSessionRepository academicSessionRepository) {
		this.orgRepository = orgRepository;
		this.courseRepository = courseRepository;
		this.clazzRepository = clazzRepository;
		this.enrollmentRepository = enrollmentRepository;
		this.userRepository = userRepository;
		this.academicSessionRepository = academicSessionRepository;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<Org> getOrgs(Specification<Org> filters, Pageable page) {
		Page<Org> orgs = orgRepository.findAll(filters, page);

		return orgs;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Org getOrg(String sourcedId) {
		Org org = orgRepository.findOne(sourcedId);

		if (org == null)
			throw new NotFoundException("Org not found: " + sourcedId);

		return org;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<Org> getSchools(Specification<Org> filters, Pageable page) {
		Specifications<Org> specs = Specifications.where(OrgSpecs.byType(OrgType.SCHOOL)).and(filters);

		Page<Org> schools = orgRepository.findAll(specs, page);

		return schools;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Org getSchool(String sourcedId) {
		Org school = orgRepository.findOne(OrgSpecs.byTypeAndSourcedId(OrgType.SCHOOL, sourcedId));

		if (school == null)
			throw new NotFoundException("School not found: " + sourcedId);

		return school;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<Course> getCoursesForSchool(String sourcedId, Specification<Course> filters, Pageable page) {
		this.validateSchool(sourcedId);

		Specifications<Course> specs = Specifications.where(CourseSpecs.byOrgSourcedId(sourcedId)).and(filters);

		Page<Course> courses = courseRepository.findAll(specs, page);

		return courses;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<Clazz> getClassesForSchool(String sourcedId, Specification<Clazz> filters, Pageable page) {
		this.validateSchool(sourcedId);

		Specifications<Clazz> specs = Specifications.where(ClazzSpecs.bySchoolSourcedId(sourcedId)).and(filters);

		Page<Clazz> clazzes = clazzRepository.findAll(specs, page);

		return clazzes;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<Enrollment> getEnrollmentsForClassInSchool(String schoolSourcedId, String clazzSourcedId,
			Specification<Enrollment> filters, Pageable page) {
		this.validateSchool(schoolSourcedId);
		this.validateClazz(clazzSourcedId);

		Specifications<Enrollment> specs = Specifications
				.where(EnrollmentSpecs.bySchoolSourcedIdAndClazzSourcedId(schoolSourcedId, clazzSourcedId))
				.and(filters);

		Page<Enrollment> enrollments = enrollmentRepository.findAll(specs, page);

		return enrollments;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<User> getStudentsForClassInSchool(String schoolSourcedId, String clazzSourcedId,
			Specification<User> filters, Pageable page) {
		this.validateSchool(schoolSourcedId);
		this.validateClazz(clazzSourcedId);

		List<Enrollment> enrollments = enrollmentRepository.findAll(EnrollmentSpecs
				.byRoleAndSchoolSourcedIdAndClazzSourcedId(Role.STUDENT, schoolSourcedId, clazzSourcedId));

		List<String> studentSourcedIds = enrollments.stream().map(enrollment -> enrollment.getUser().getSourcedId())
				.collect(Collectors.toList());

		Specifications<User> specs = Specifications.where(UserSpecs.byUserSourcedIds(studentSourcedIds)).and(filters);

		Page<User> students = userRepository.findAll(specs, page);

		return students;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<User> getTeachersForClassInSchool(String schoolSourcedId, String clazzSourcedId,
			Specification<User> filters, Pageable page) {
		this.validateSchool(schoolSourcedId);
		this.validateClazz(clazzSourcedId);

		List<Enrollment> enrollments = enrollmentRepository.findAll(EnrollmentSpecs
				.byRoleAndSchoolSourcedIdAndClazzSourcedId(Role.TEACHER, schoolSourcedId, clazzSourcedId));

		List<String> teacherSourcedIds = enrollments.stream().map(enrollment -> enrollment.getUser().getSourcedId())
				.collect(Collectors.toList());

		Specifications<User> specs = Specifications.where(UserSpecs.byUserSourcedIds(teacherSourcedIds)).and(filters);

		Page<User> teachers = userRepository.findAll(specs, page);

		return teachers;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<Enrollment> getEnrollmentsForSchool(String sourcedId, Specification<Enrollment> filters,
			Pageable page) {
		this.validateSchool(sourcedId);

		Specifications<Enrollment> specs = Specifications.where(EnrollmentSpecs.bySchoolSourcedId(sourcedId))
				.and(filters);

		Page<Enrollment> enrollments = enrollmentRepository.findAll(specs, page);

		return enrollments;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<User> getStudentsForSchool(String sourcedId, Specification<User> filters, Pageable page) {
		this.validateSchool(sourcedId);

		Specifications<User> specs = Specifications.where(UserSpecs.byRoleAndSchoolSourcedId(Role.STUDENT, sourcedId))
				.and(filters);

		Page<User> students = userRepository.findAll(specs, page);

		return students;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<User> getTeachersForSchool(String sourcedId, Specification<User> filters, Pageable page) {
		this.validateSchool(sourcedId);

		Specifications<User> specs = Specifications.where(UserSpecs.byRoleAndSchoolSourcedId(Role.TEACHER, sourcedId))
				.and(filters);

		Page<User> teachers = userRepository.findAll(specs, page);

		return teachers;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<AcademicSession> getTermsForSchool(String sourcedId, Specification<AcademicSession> filters,
			Pageable page) {
		this.validateSchool(sourcedId);

		List<Clazz> clazzes = clazzRepository.findAll(ClazzSpecs.bySchoolSourcedId(sourcedId));

		List<String> termSourcedIds = clazzes.stream().flatMap(clazz -> clazz.getTerms().stream())
				.map(term -> term.getSourcedId()).collect(Collectors.toList());

		Specifications<AcademicSession> specs = Specifications
				.where(AcademicSessionSpecs.byTermSourcedIds(termSourcedIds)).and(filters);

		Page<AcademicSession> terms = academicSessionRepository.findAll(specs, page);

		return terms;
	}

	public void validateSchool(String sourcedId) {
		Org school = orgRepository.findOne(OrgSpecs.byTypeAndSourcedId(OrgType.SCHOOL, sourcedId));

		if (school == null)
			throw new NotFoundException("School not found: " + sourcedId);
	}

	public void validateClazz(String sourcedId) {
		Clazz clazz = clazzRepository.findOne(sourcedId);

		if (clazz == null)
			throw new NotFoundException("Class not found: " + sourcedId);
	}
}