package no.iktsenteret.pifu.service.enrollment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import no.iktsenteret.pifu.domain.Enrollment;

public interface EnrollmentService {

	Page<Enrollment> getEnrollments(Specification<Enrollment> filters, Pageable page);

	Enrollment getEnrollment(String sourcedId);
}
