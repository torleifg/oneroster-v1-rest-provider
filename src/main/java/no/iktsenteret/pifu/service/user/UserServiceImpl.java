package no.iktsenteret.pifu.service.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import no.iktsenteret.pifu.domain.Clazz;
import no.iktsenteret.pifu.domain.Enrollment;
import no.iktsenteret.pifu.domain.User;
import no.iktsenteret.pifu.domain.vocab.Role;
import no.iktsenteret.pifu.service.clazz.ClazzRepository;
import no.iktsenteret.pifu.service.clazz.ClazzSpecs;
import no.iktsenteret.pifu.service.enrollment.EnrollmentRepository;
import no.iktsenteret.pifu.service.enrollment.EnrollmentSpecs;
import no.iktsenteret.pifu.web.exception.NotFoundException;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final ClazzRepository clazzRepository;

	private final EnrollmentRepository enrollmentRepository;

	public UserServiceImpl(UserRepository userRepository, ClazzRepository clazzRepository,
			EnrollmentRepository enrollmentRepository) {
		this.userRepository = userRepository;
		this.clazzRepository = clazzRepository;
		this.enrollmentRepository = enrollmentRepository;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<User> getUsers(Specification<User> filters, Pageable page) {
		Page<User> users = userRepository.findAll(filters, page);

		return users;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public User getUser(String sourcedId) {
		User user = userRepository.findOne(sourcedId);

		if (user == null)
			throw new NotFoundException("User not found: " + sourcedId);

		return user;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<User> getTeachers(Specification<User> filters, Pageable page) {
		Specifications<User> specs = Specifications.where(UserSpecs.byRole(Role.TEACHER)).and(filters).and(filters);

		Page<User> teachers = userRepository.findAll(specs, page);

		return teachers;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public User getTeacher(String sourcedId) {
		User teacher = userRepository.findOne(UserSpecs.byRoleAndSourcedId(Role.TEACHER, sourcedId));

		if (teacher == null)
			throw new NotFoundException("Teacher not found: " + sourcedId);

		return teacher;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<User> getStudents(Specification<User> filters, Pageable page) {
		Specifications<User> specs = Specifications.where(UserSpecs.byRole(Role.STUDENT)).and(filters);

		Page<User> students = userRepository.findAll(specs, page);

		return students;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public User getStudent(String sourcedId) {
		User student = userRepository.findOne(UserSpecs.byRoleAndSourcedId(Role.STUDENT, sourcedId));

		if (student == null)
			throw new NotFoundException("Student not found: " + sourcedId);

		return student;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<Clazz> getClassesForStudent(String sourcedId, Specification<Clazz> filters, Pageable page) {
		User student = userRepository.findOne(UserSpecs.byRoleAndSourcedId(Role.STUDENT, sourcedId));

		if (student == null)
			throw new NotFoundException("Student not found: " + sourcedId);

		List<Enrollment> enrollments = enrollmentRepository.findAll(EnrollmentSpecs.byUserSourcedId(sourcedId));

		List<String> clazzSourcedIds = enrollments.stream().map(enrollment -> enrollment.getClazz().getSourcedId())
				.collect(Collectors.toList());

		Specifications<Clazz> specs = Specifications.where(ClazzSpecs.byClazzSourcedIds(clazzSourcedIds)).and(filters);

		Page<Clazz> clazzes = clazzRepository.findAll(specs, page);

		return clazzes;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<Clazz> getClassesForTeacher(String sourcedId, Specification<Clazz> filters, Pageable page) {
		User teacher = userRepository.findOne(UserSpecs.byRoleAndSourcedId(Role.TEACHER, sourcedId));

		if (teacher == null)
			throw new NotFoundException("Teacher not found: " + sourcedId);

		List<Enrollment> enrollments = enrollmentRepository.findAll(EnrollmentSpecs.byUserSourcedId(sourcedId));

		List<String> clazzSourcedIds = enrollments.stream().map(enrollment -> enrollment.getClazz().getSourcedId())
				.collect(Collectors.toList());

		Specifications<Clazz> specs = Specifications.where(ClazzSpecs.byClazzSourcedIds(clazzSourcedIds)).and(filters);

		Page<Clazz> clazzes = clazzRepository.findAll(specs, page);

		return clazzes;
	}
}