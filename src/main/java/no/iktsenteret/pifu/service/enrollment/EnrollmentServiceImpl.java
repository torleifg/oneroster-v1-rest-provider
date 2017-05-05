package no.iktsenteret.pifu.service.enrollment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import no.iktsenteret.pifu.domain.Enrollment;
import no.iktsenteret.pifu.web.exception.NotFoundException;

@Service
@Transactional(readOnly = true)
public class EnrollmentServiceImpl implements EnrollmentService {

	private final EnrollmentRepository enrollmentRepository;

	public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
		this.enrollmentRepository = enrollmentRepository;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<Enrollment> getEnrollments(Specification<Enrollment> filters, Pageable page) {
		Page<Enrollment> enrollments = enrollmentRepository.findAll(filters, page);

		return enrollments;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Enrollment getEnrollment(String sourcedId) {
		Enrollment enrollment = enrollmentRepository.findOne(sourcedId);

		if (enrollment == null)
			throw new NotFoundException("Enrollment not found: " + sourcedId);

		return enrollment;
	}

}
