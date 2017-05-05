package no.iktsenteret.pifu.service.clazz;

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
import no.iktsenteret.pifu.domain.LineItem;
import no.iktsenteret.pifu.domain.Result;
import no.iktsenteret.pifu.domain.User;
import no.iktsenteret.pifu.domain.vocab.Role;
import no.iktsenteret.pifu.service.enrollment.EnrollmentRepository;
import no.iktsenteret.pifu.service.enrollment.EnrollmentSpecs;
import no.iktsenteret.pifu.service.lineitem.LineItemRepository;
import no.iktsenteret.pifu.service.lineitem.LineItemSpecs;
import no.iktsenteret.pifu.service.result.ResultRepository;
import no.iktsenteret.pifu.service.result.ResultSpecs;
import no.iktsenteret.pifu.service.user.UserRepository;
import no.iktsenteret.pifu.service.user.UserSpecs;
import no.iktsenteret.pifu.web.exception.NotFoundException;

@Service
@Transactional(readOnly = true)
public class ClazzServiceImpl implements ClazzService {

	private final ClazzRepository clazzRepository;

	private final EnrollmentRepository enrollmentRepository;

	private final UserRepository userRepository;

	private final ResultRepository resultRepository;

	private final LineItemRepository lineItemRepository;

	public ClazzServiceImpl(ClazzRepository clazzRepository, EnrollmentRepository enrollmentRepository,
			UserRepository userRepository, ResultRepository resultRepository, LineItemRepository lineItemRepository) {
		this.clazzRepository = clazzRepository;
		this.enrollmentRepository = enrollmentRepository;
		this.userRepository = userRepository;
		this.resultRepository = resultRepository;
		this.lineItemRepository = lineItemRepository;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<Clazz> getClasses(Specification<Clazz> filters, Pageable page) {
		Page<Clazz> clazzes = clazzRepository.findAll(filters, page);

		return clazzes;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Clazz getClass(String sourcedId) {
		Clazz clazz = clazzRepository.findOne(sourcedId);

		if (clazz == null)
			throw new NotFoundException("Class not found: " + sourcedId);

		return clazz;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<User> getStudentsForClass(String sourcedId, Specification<User> filters, Pageable page) {
		this.validateClazz(sourcedId);

		List<Enrollment> enrollments = enrollmentRepository
				.findAll(EnrollmentSpecs.findByRoleAndClazzSourcedId(Role.STUDENT, sourcedId));

		List<String> studentSourcedIds = enrollments.stream().map(enrollment -> enrollment.getUser().getSourcedId())
				.collect(Collectors.toList());

		Specifications<User> specs = Specifications.where(UserSpecs.byUserSourcedIds(studentSourcedIds)).and(filters);

		Page<User> students = userRepository.findAll(specs, page);

		return students;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<User> getTeachersForClass(String sourcedId, Specification<User> filters, Pageable page) {
		this.validateClazz(sourcedId);

		List<Enrollment> enrollments = enrollmentRepository
				.findAll(EnrollmentSpecs.findByRoleAndClazzSourcedId(Role.TEACHER, sourcedId));

		List<String> teacherSourcedIds = enrollments.stream().map(enrollment -> enrollment.getUser().getSourcedId())
				.collect(Collectors.toList());

		Specifications<User> specs = Specifications.where(UserSpecs.byUserSourcedIds(teacherSourcedIds)).and(filters);

		Page<User> teachers = userRepository.findAll(specs, page);

		return teachers;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<Result> getResultsForClass(String sourcedId, Specification<Result> filters, Pageable page) {
		this.validateClazz(sourcedId);

		List<LineItem> lineItems = lineItemRepository.findAll(LineItemSpecs.ByClazzSourcedId(sourcedId));

		List<String> lineItemSourcedIds = lineItems.stream().map(lineItem -> lineItem.getSourcedId())
				.collect(Collectors.toList());

		Specifications<Result> specs = Specifications.where(ResultSpecs.byLineItemSourcedIds(lineItemSourcedIds))
				.and(filters);

		Page<Result> results = resultRepository.findAll(specs, page);

		return results;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<LineItem> getLineItemsForClass(String sourcedId, Specification<LineItem> filters, Pageable page) {
		this.validateClazz(sourcedId);

		Specifications<LineItem> specs = Specifications.where(LineItemSpecs.ByClazzSourcedId(sourcedId)).and(filters);

		Page<LineItem> lineItems = lineItemRepository.findAll(specs, page);

		return lineItems;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<Result> getResultsForLineItemForClass(String clazzSourcedId, String lineItemSourcedId,
			Specification<Result> filters, Pageable page) {
		this.validateClazz(clazzSourcedId);

		LineItem lineItem = lineItemRepository.findOne(lineItemSourcedId);

		if (lineItem == null)
			throw new NotFoundException("LineItem not found: " + lineItemSourcedId);

		Specifications<Result> specs = Specifications.where(ResultSpecs.byLineItemSourcedId(lineItemSourcedId))
				.and(filters);

		Page<Result> results = resultRepository.findAll(specs, page);

		return results;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<Result> getResultsForStudentsForClass(String clazzSourcedId, String studentSourcedId,
			Specification<Result> filters, Pageable page) {
		this.validateClazz(clazzSourcedId);

		User student = userRepository.findOne(UserSpecs.byRoleAndSourcedId(Role.STUDENT, studentSourcedId));

		if (student == null)
			throw new NotFoundException("Student not found: " + studentSourcedId);

		Specifications<Result> specs = Specifications.where(ResultSpecs.byStudentSourcedId(studentSourcedId))
				.and(filters);

		Page<Result> results = resultRepository.findAll(specs, page);

		return results;
	}

	public void validateClazz(String sourcedId) {
		Clazz clazz = clazzRepository.findOne(sourcedId);

		if (clazz == null)
			throw new NotFoundException("Class not found: " + sourcedId);
	}
}